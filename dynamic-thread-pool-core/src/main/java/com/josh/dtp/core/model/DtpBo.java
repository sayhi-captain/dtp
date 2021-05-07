package com.josh.dtp.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author : Josh
 * @date : 2020-08-04 17:20
 */
@Data
@Accessors(chain = true)
public class DtpBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端标识
     */
    private String appId;

    /**
     * 集群标识
     */
    private String clusterId;

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 核心线程数
     */
    private Integer corePoolSize;

    /**
     * 最大线程数
     */
    private Integer maximumPoolSize;

    /**
     * 空闲线程存活时长
     */
    private Long keepAliveSeconds;

    /**
     * 队列长度
     */
    private Integer queueCapacity;

    /**
     * 队列class
     */
    private String queueName;

    /**
     * 拒绝策略
     */
    private String rejectedExecutionHandlerName;
}
