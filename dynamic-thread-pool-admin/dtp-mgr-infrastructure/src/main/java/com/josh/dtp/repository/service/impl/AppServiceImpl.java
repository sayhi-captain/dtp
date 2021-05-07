package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.App;
import com.josh.dtp.repository.mapper.AppMapper;
import com.josh.dtp.repository.service.AppService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端应用表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

}
