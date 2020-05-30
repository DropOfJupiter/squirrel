package org.squirrelframework.foundation.fsm.order;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-28 18:47
 */
@Slf4j
public class OrderChargeBackStateMachine extends AbstractStateMachine< OrderChargeBackStateMachine, OrderChargeBackTaskState, OrderChargeBackTaskEvent, OrderChargeBackTaskModel> {

	private String sysLogger;

	public void entryINIT(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="entryINIT----context:"+ JSON.toJSONString(context)+",to.name:"+to.name();
		context.setOprUserId(context.getOprUserId()+1);
		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
		int i=1/0;
		System.out.println(sysLogger);
	}

	public void exitINIT(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="exitINIT----context:"+ JSON.toJSONString(context)+",from.name:"+from.name();
		context.setOprUserId(context.getOprUserId()+1);
		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
		System.out.println(sysLogger);
	}

	public void transitFromINITToREFUNDING_BILLSOnORDER_AND_CHARGEBACK_RECORD_EXIST(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="transitFromINITToREFUNDING_BILLSOnORDER_AND_CHARGEBACK_RECORD_EXIST----context:"+ JSON.toJSONString(context)+",from.name:"+from.name()+",to.name:"+to.name();
		context.setOprUserId(context.getOprUserId()+1);
		this.fire(OrderChargeBackTaskEvent.ORDER_AND_CHARGEBACK_RECORD_EXIST,context);
		System.out.println(sysLogger);
	}

	public void entryREFUNDING_BILLS(OrderChargeBackTaskState from, OrderChargeBackTaskState to, OrderChargeBackTaskEvent event,OrderChargeBackTaskModel context) {
		sysLogger="entryREFUNDING_BILLS----context:"+ JSON.toJSONString(context)+",to.name:"+to.name();
		System.out.println(sysLogger);
	}
}
