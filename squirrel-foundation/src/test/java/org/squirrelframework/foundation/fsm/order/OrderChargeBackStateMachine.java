package org.squirrelframework.foundation.fsm.order;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.StateMachineLogger;
import org.squirrelframework.foundation.fsm.annotation.LogExecTime;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-28 18:47
 */
@Slf4j
public class OrderChargeBackStateMachine extends AbstractStateMachine< OrderChargeBackStateMachine, OrderChargeBackTaskState, OrderChargeBackTaskEvent, OrderChargeBackTaskModel> {

	private String sysLogger;

	@LogExecTime
	public void entryINIT(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="entryINIT----context:"+ JSON.toJSONString(context)+",to.name:"+to.name();
		context.setOprUserId(context.getOprUserId()+1);
		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
		System.out.println(sysLogger);
	}

//	public void exitINIT(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
//		sysLogger="exitINIT----context:"+ JSON.toJSONString(context)+",from.name:"+from.name();
//		context.setOprUserId(context.getOprUserId()+1);
//		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
//		System.out.println(sysLogger);
//	}
//
//	public void transitFromINITToREFUNDING_BILLSOnORDER_AND_CHARGEBACK_RECORD_EXIST(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
//		sysLogger="transitFromINITToREFUNDING_BILLSOnORDER_AND_CHARGEBACK_RECORD_EXIST----context:"+ JSON.toJSONString(context)+",from.name:"+from.name()+",to.name:"+to.name();
//		context.setOprUserId(context.getOprUserId()+1);
//		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
//		System.out.println(sysLogger);
//	}

	public void entryREFUNDING_BILLS(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) throws InterruptedException {
		sysLogger=System.currentTimeMillis() +" entryREFUNDING_BILLS----context:"+ JSON.toJSONString(context)+",to.name:"+to.name();
		System.out.println(sysLogger);
		if(context.getOprUserId()<6){
			long retry=context.getOprUserId()-1L;
			context.setRecordUUID("第"+retry+"次重试");
			context.setOprUserId(context.getOprUserId()+1);
			Thread.sleep(1000);
			this.fire(OrderChargeBackTaskEvent.CALL_FINANCE_API_FAILED,context);
		}else {
			context.setOprUserId(context.getOprUserId()+1);
			context.setRecordUUID("222");
			this.fire(OrderChargeBackTaskEvent.CALL_FINANCE_API_SUCCESS,context);
		}
	}

	public void entryREFUND(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="entryREFUND----context:"+ JSON.toJSONString(context)+",to.name:"+to.name();
		if(context.getOprUserId()==5){
			context.setOprUserId(context.getOprUserId()+1);
			this.fire(OrderChargeBackTaskEvent.CALL_PAYMENT_API_FAILED,context);
		}else {
			context.setOprUserId(context.getOprUserId()+1);
			this.fire(OrderChargeBackTaskEvent.CALL_PAYMENT_API_SUCCESS,context);
		}
		System.out.println(sysLogger);
	}


}
