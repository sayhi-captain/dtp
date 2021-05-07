package com.josh.dtp.client.annotation;


import com.josh.dtp.client.enums.DtpQueueEnum;
import com.josh.dtp.client.enums.RejectedExecutionHandlerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述: 动态线程池注解类
 *
 * @author Josh
 * @date 2021/4/25 4:32 下午
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Dtp {
    /**
     * 线程池名称 - 作为线程池的唯一键
     *
     * @return
     */
    String name();

    /**
     * 核心线程数
     *
     * @return
     */
    int corePoolSize() default 5;

    /**
     * 最大线程数
     *
     * @return
     */
    int maximumPoolSize() default 5;

    /**
     * 队列容量
     *
     * @return
     */
    int queueCapacity() default 50;

    /**
     * 线程空闲存活时间 单位:秒
     *
     * @return
     */
    long keepAliveSeconds() default 60L;

    /**
     * 队列类型
     *
     * @return
     */
    DtpQueueEnum queueEnum() default DtpQueueEnum.resizableCapacityLinkedBlockIngQueue;

    /**
     * 拒绝策略类型
     *
     * @return
     */
    RejectedExecutionHandlerEnum rejectedExecutionHandlerEnum() default RejectedExecutionHandlerEnum.abortPolicy;
}
