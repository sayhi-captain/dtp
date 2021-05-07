package com.josh.dtp.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 描述: 自动配置类
 *
 * @author Josh
 * @date 2021/4/25 5:16 下午
 */
@Configuration
@Conditional(value = DtpCondition.class)
@Import(DtpRegistrar.class)
public class DtpAutoConfiguration {

    @Bean
    public DtpConfigProperties dtpConfigProperties() {
        return new DtpConfigProperties();
    }
}
