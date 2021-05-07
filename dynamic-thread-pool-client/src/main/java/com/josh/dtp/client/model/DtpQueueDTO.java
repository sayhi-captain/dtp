package com.josh.dtp.client.model;

import com.josh.dtp.client.enums.DtpQueueEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述: 队列dto对象
 *
 * @author Josh
 * @date 2021/4/26 10:12 上午
 */
@Data
@Accessors(chain = true)
public class DtpQueueDTO {
    /**
     * 线程池名称
     */
    private String poolName;
    /**
     * 队列枚举
     */
    private DtpQueueEnum queueEnum;
    /**
     * 队列容量
     */
    private Integer queueCapacity;
}
