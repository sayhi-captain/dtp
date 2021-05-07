package com.josh.dtp.client.configuration;

import com.josh.dtp.client.annotation.DtpCollectProcessor;
import com.josh.dtp.client.annotation.DtpInjectorContainer;
import com.josh.dtp.client.support.DtpCoreSupport;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 描述: 注册初始化的bean到spring容器中
 *
 * @author Josh
 * @date 2021/4/25 6:10 下午
 */
public class DtpRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 注册dtp处理容器类
     */
    private static final String DTP_HANDLER_CONTAINER = "com.josh.dtp.client.annotation.DtpHandlerContainer";

    /**
     * 收集dtp注解
     * 包装为处理类并放到容器中
     */
    private static final String DTP_COLLECT_PROCESSOR = "com.josh.dtp.client.annotation.DtpCollectProcessor";

    /**
     * 初始化及设置配置变更监听等流程
     */
    private static final String DTP_SUPPORT = "com.josh.dtp.client.support.DtpCoreSupport";

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition dtpHandlerContainerRootBean = new RootBeanDefinition(DtpInjectorContainer.class);
        registry.registerBeanDefinition(DTP_HANDLER_CONTAINER, dtpHandlerContainerRootBean);

        RootBeanDefinition dtpCollectProcessorRootBean = new RootBeanDefinition(DtpCollectProcessor.class);
        registry.registerBeanDefinition(DTP_COLLECT_PROCESSOR, dtpCollectProcessorRootBean);

        RootBeanDefinition dtpSupportRootBean = new RootBeanDefinition(DtpCoreSupport.class);
        registry.registerBeanDefinition(DTP_SUPPORT, dtpSupportRootBean);
    }
}
