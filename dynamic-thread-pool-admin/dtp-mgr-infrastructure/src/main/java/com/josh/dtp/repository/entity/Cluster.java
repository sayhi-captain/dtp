package com.josh.dtp.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 集群表
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cluster")
public class Cluster implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * AppId
     */
    private String appId;

    /**
     * 集群名字
     */
    private String clusterName;

    /**
     * cluster Id
     */
    private String clusterId;

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
