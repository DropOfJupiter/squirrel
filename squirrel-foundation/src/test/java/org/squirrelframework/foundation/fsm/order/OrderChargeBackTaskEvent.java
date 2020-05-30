package org.squirrelframework.foundation.fsm.order;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-28 18:41
 */
public enum OrderChargeBackTaskEvent {
	ORDER_NOT_EXIST,
	ORDER_CHARGEBACK_RECORD_NOT_EXIST,
	ORDER_AND_CHARGEBACK_RECORD_EXIST,
	CALL_FINANCE_API_FAILED,
	CALL_FINANCE_API_SUCCESS,
	CALL_PAYMENT_API_FAILED,
	CALL_PAYMENT_API_SUCCESS,
	DATA_SAVE_SUCCESS;
}
