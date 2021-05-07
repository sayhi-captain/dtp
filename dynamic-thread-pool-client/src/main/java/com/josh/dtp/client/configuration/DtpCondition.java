package com.josh.dtp.client.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 描述: dtp加载条件
 *
 * @author Josh
 * @date 2021/4/27 10:30 上午
 */
public class DtpCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        Boolean enable = environment.getProperty("dtp.enable", Boolean.class);
        return enable != null && enable;
    }
}
