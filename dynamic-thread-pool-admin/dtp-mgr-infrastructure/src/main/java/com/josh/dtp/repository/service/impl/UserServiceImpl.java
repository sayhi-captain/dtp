package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.User;
import com.josh.dtp.repository.mapper.UserMapper;
import com.josh.dtp.repository.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
