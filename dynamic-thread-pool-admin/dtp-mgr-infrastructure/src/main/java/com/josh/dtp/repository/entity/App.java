package com.josh.dtp.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 客户端应用表
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("app")
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * AppID
     */
    private String appId;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 1: deleted, 0: normal
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
