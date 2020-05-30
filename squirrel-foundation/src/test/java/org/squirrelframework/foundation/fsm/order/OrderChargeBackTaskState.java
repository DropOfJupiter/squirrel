package org.squirrelframework.foundation.fsm.order;


/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-28 18:41
 */
public enum OrderChargeBackTaskState {
    INIT,
    REFUNDING_BILLS,//更新可退款的账单为退款中
    REFUND,//退款
    REFUNDED_BILLS,//更新可退款的账单为已退款
    MARK_REFUNDED_BILLS_INVALID,//标记已退款的账单失效
    SAVE_RESULT,//保存
    FAILED,
    SUCCESS;
}
