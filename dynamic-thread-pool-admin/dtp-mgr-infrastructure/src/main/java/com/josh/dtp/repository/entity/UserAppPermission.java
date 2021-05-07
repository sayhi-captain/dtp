package com.josh.dtp.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_app_permission")
public class UserAppPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(value = "user_app_permission_id", type = IdType.AUTO)
    private Integer userAppPermissionId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 权限
     */
    private String permission;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 修改时间
     */
    private Long modified;


}
