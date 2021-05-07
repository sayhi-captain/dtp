package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.Dtp;
import com.josh.dtp.repository.mapper.DtpMapper;
import com.josh.dtp.repository.service.DtpService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态线程池表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class DtpServiceImpl extends ServiceImpl<DtpMapper, Dtp> implements DtpService {

}
