package org.squirrelframework.foundation.fsm.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author qiutt
 * @description:no description
 * @create 2020-05-28 18:41
 */
@AllArgsConstructor
@Data
public class OrderChargeBackTaskModel implements Serializable {

    private static final long serialVersionUID = 6922181996345386439L;
    /**
     * 订单UUID
     */
    private String orderUUID;
    /**
     * 退单记录UUID
     */
    private String recordUUID;
    /**
     * 退单操作人ID
     */
    private Long oprUserId;


}
