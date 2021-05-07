package com.josh.dtp.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 动态线程池日志表
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dtp_log")
public class DtpLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(value = "dtp_log_id", type = IdType.AUTO)
    private Integer dtpLogId;

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
     * 客户端ip
     */
    private String ip;

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
     * 活跃线程数
     */
    private Integer activeCount;

    /**
     * 线程池 完成任务数
     */
    private Long completedTaskCount;

    /**
     * 队列长度
     */
    private Integer queueSize;

    /**
     * 队列剩余长度
     */
    private Integer queueRemainingCapacity;

    /**
     * 线程池曾最大线程数
     */
    private Integer largestPoolSize;

    /**
     * 拒绝任务数量
     */
    private Integer rejectedExecutionCount;

    /**
     * 线程池大小
     */
    private Integer poolSize;

    /**
     * 线程池任务数
     */
    private Long taskCount;

    /**
     * 总执行时间
     */
    private Long totalTime;

    /**
     * 曾最大执行时间
     */
    private Long maximumTime;

    /**
     * 记录时间
     */
    private Long logTime;

    /**
     * 删除标识 0：未删除 1：已删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 修改时间
     */
    private Long modified;


}
