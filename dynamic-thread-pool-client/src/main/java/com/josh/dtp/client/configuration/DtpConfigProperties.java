package com.josh.dtp.client.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 描述: 动态线程池配置项
 *
 * @author Josh
 * @date 2021/4/26 2:13 下午
 */
@Data
public class DtpConfigProperties {
    /**
     * 客户端id
     */
    @Value("{dtp.app.id}")
    private String appId;
    /**
     * 集群id
     */
    @Value("{dtp.cluster.id}")
    private String clusterId;
    /**
     * 后台管理地址: 多个地址使用,分割
     */
    @Value("{dtp.admin.url}")
    private String adminUrl;
    /**
     * 连接重试时长
     */
    @Value("{dtp.connect.retry.interval}")
    private long connectRetryInterval;
}
