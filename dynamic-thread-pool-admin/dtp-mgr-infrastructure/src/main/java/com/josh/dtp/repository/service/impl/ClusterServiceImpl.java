package com.josh.dtp.repository.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.josh.dtp.repository.entity.Cluster;
import com.josh.dtp.repository.mapper.ClusterMapper;
import com.josh.dtp.repository.service.ClusterService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 集群表 服务实现类
 * </p>
 *
 * @author Josh
 * @since 2021-04-28
 */
@Service
public class ClusterServiceImpl extends ServiceImpl<ClusterMapper, Cluster> implements ClusterService {

}
