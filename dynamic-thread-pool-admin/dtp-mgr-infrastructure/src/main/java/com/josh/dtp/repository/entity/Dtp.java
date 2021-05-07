package com.josh.dtp.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 动态线程池表
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dtp")
public class Dtp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "dtp_id", type = IdType.AUTO)
    private Integer dtpId;

    /**
     * 客户端标识
     */
    private String appId;

    /**
     * 集群标识
     */
    private String clusterId;

    /**
     * 线程池标题
     */
    private String title;

    /**
     * 负责人
     */
    private String ownerName;

    /**
     * 告警邮箱
     */
    private String alarmEmail;

    /**
     * 是否开始告警
     */
    private Boolean openAlarm;

    /**
     * 线程池告警阈值
     */
    private Integer poolAlarmThreshold;

    /**
     * 队列告警阈值
     */
    private Integer queueAlarmThreshold;

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
     * 队列名称
     */
    private String queueName;

    /**
     * 拒绝策略
     */
    private String rejectedExecutionHandlerName;

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
