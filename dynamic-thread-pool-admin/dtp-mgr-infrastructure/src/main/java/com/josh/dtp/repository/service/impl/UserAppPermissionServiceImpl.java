package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.UserAppPermission;
import com.josh.dtp.repository.mapper.UserAppPermissionMapper;
import com.josh.dtp.repository.service.UserAppPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户权限表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class UserAppPermissionServiceImpl extends ServiceImpl<UserAppPermissionMapper, UserAppPermission> implements UserAppPermissionService {

}
