package com.josh.dtp.client.model;

import com.josh.dtp.client.enums.DtpQueueEnum;
import com.josh.dtp.client.enums.RejectedExecutionHandlerEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 描述: 线程池dto对象
 *
 * @author Josh
 * @date 2021/4/26 10:45 上午
 */
@Data
@Accessors(chain = true)
public class DtpThreadPoolDTO {
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
    /**
     * 核心线程数
     */
    private Integer corePoolSize;
    /**
     * 最大线程数
     */
    private Integer maximumPoolSize;
    /**
     * 空闲线程最大存活时间
     */
    private Long keepAliveSeconds;
    /**
     * 拒绝策略
     */
    private RejectedExecutionHandlerEnum rejectedExecutionHandlerEnum;
}
