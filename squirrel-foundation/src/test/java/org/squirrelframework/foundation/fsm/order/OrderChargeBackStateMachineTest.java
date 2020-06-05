package org.squirrelframework.foundation.fsm.order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.squirrelframework.foundation.fsm.*;
import org.squirrelframework.foundation.fsm.atmqiutt.atm.ATMStateMachine;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-30 20:47
 */
public class OrderChargeBackStateMachineTest {

	private OrderChargeBackStateMachine stateMachine;

	@Before
	public void setup() {
		StateMachineBuilder<OrderChargeBackStateMachine, OrderChargeBackTaskState, OrderChargeBackTaskEvent, OrderChargeBackTaskModel> builder
				= StateMachineBuilderFactory.create(
				OrderChargeBackStateMachine.class, OrderChargeBackTaskState.class, OrderChargeBackTaskEvent.class, OrderChargeBackTaskModel.class);
		//流程注册
		builder.externalTransition().from(OrderChargeBackTaskState.INIT).to(OrderChargeBackTaskState.FAILED).on(OrderChargeBackTaskEvent.ORDER_NOT_EXIST);
		builder.externalTransition().from(OrderChargeBackTaskState.INIT).to(OrderChargeBackTaskState.FAILED).on(OrderChargeBackTaskEvent.ORDER_CHARGEBACK_RECORD_NOT_EXIST);
		builder.externalTransition().from(OrderChargeBackTaskState.INIT).to(OrderChargeBackTaskState.REFUNDING_BILLS).on(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST);

		builder.externalTransition().from(OrderChargeBackTaskState.REFUNDING_BILLS).to(OrderChargeBackTaskState.FAILED).on(OrderChargeBackTaskEvent.ORDER_CHARGEBACK_RECORD_NOT_EXIST);
		//内部重试
		builder.internalTransition(TransitionPriority.HIGH).within(OrderChargeBackTaskState.REFUNDING_BILLS).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_FAILED).callMethod("entryREFUNDING_BILLS");
		builder.externalTransition().from(OrderChargeBackTaskState.REFUNDING_BILLS).to(OrderChargeBackTaskState.REFUND).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_SUCCESS);

		builder.externalTransition().from(OrderChargeBackTaskState.REFUND).to(OrderChargeBackTaskState.FAILED).on(OrderChargeBackTaskEvent.ORDER_CHARGEBACK_RECORD_NOT_EXIST);
		builder.externalTransition().from(OrderChargeBackTaskState.REFUND).to(OrderChargeBackTaskState.REFUND).on(OrderChargeBackTaskEvent.CALL_PAYMENT_API_FAILED);
		builder.externalTransition().from(OrderChargeBackTaskState.REFUND).to(OrderChargeBackTaskState.REFUNDED_BILLS).on(OrderChargeBackTaskEvent.CALL_PAYMENT_API_SUCCESS);

		builder.externalTransition().from(OrderChargeBackTaskState.REFUNDED_BILLS).to(OrderChargeBackTaskState.FAILED).on(OrderChargeBackTaskEvent.ORDER_CHARGEBACK_RECORD_NOT_EXIST);
		builder.externalTransition().from(OrderChargeBackTaskState.REFUNDED_BILLS).to(OrderChargeBackTaskState.REFUNDED_BILLS).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_FAILED);
		builder.externalTransition().from(OrderChargeBackTaskState.REFUNDED_BILLS).to(OrderChargeBackTaskState.MARK_REFUNDED_BILLS_INVALID).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_SUCCESS);

		builder.externalTransition().from(OrderChargeBackTaskState.MARK_REFUNDED_BILLS_INVALID).to(OrderChargeBackTaskState.MARK_REFUNDED_BILLS_INVALID).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_FAILED);
		builder.externalTransition().from(OrderChargeBackTaskState.MARK_REFUNDED_BILLS_INVALID).to(OrderChargeBackTaskState.SAVE_RESULT).on(OrderChargeBackTaskEvent.CALL_FINANCE_API_SUCCESS);

		builder.externalTransition().from(OrderChargeBackTaskState.SAVE_RESULT).to(OrderChargeBackTaskState.SUCCESS).on(OrderChargeBackTaskEvent.DATA_SAVE_SUCCESS);


		stateMachine = builder.newStateMachine(OrderChargeBackTaskState.INIT,StateMachineConfiguration.create().enableDebugMode(true));

	}

	@After
	public void teardown() {
		if(stateMachine!=null && stateMachine.getStatus()!= StateMachineStatus.TERMINATED) {
			stateMachine.terminate(null);
		}
	}

	@Test
	public void test() {
		OrderChargeBackTaskModel taskModel=new OrderChargeBackTaskModel("111","222",1L);
		stateMachine.start(taskModel);
		StateMachineLogger fsmLogger = new StateMachineLogger(stateMachine);
		fsmLogger.startLogging();
	}


}
