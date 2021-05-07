package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.DtpLog;
import com.josh.dtp.repository.mapper.DtpLogMapper;
import com.josh.dtp.repository.service.DtpLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态线程池日志表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class DtpLogServiceImpl extends ServiceImpl<DtpLogMapper, DtpLog> implements DtpLogService {

}
