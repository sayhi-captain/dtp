package com.josh.dtp.client.annotation;

import com.josh.dtp.client.annotation.impl.DtpFiledInjector;
import com.josh.dtp.client.annotation.impl.DtpMethodInjector;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;
import com.josh.dtp.client.model.DtpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * 描述: 注解收集处理类
 *
 * @author Josh
 * @date 2021/4/25 7:25 下午
 */
@Slf4j
public class DtpCollectProcessor implements BeanPostProcessor, ApplicationContextAware {

    private DtpInjectorContainer dtpInjectorContainer;

    /**
     * 知识点:
     * 1. 加载顺序 setApplicationContext方法是在 postProcessBeforeInitialization 中执行, 因此在 postProcessAfterInitialization方法中可以用从中获取到的类
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     * @see org.springframework.context.support.ApplicationContextAwareProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     * 2. 根据bean获取其中包含相应注解的变量
     * 3. 根据bean获取其中包含相应注解的方法
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Map<Method, Dtp> annotatedMethods = null;
        try {
            annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(), (MethodIntrospector.MetadataLookup<Dtp>) method -> AnnotatedElementUtils.findMergedAnnotation(method, Dtp.class));
        } catch (RuntimeException e) {
            log.error("dtp ------> method-Dtphandler resolve error for bean[" + beanName + "].", e);
        }
        if (Objects.nonNull(annotatedMethods) && !annotatedMethods.isEmpty()) {
            for (Map.Entry<Method, Dtp> methodDtpEntry : annotatedMethods.entrySet()) {
                Method method = methodDtpEntry.getKey();
                Dtp dtp = methodDtpEntry.getValue();
                if (Objects.isNull(dtp)) {
                    continue;
                }
                String name = getName(dtp, "dtp ------> method-Dtphandler name invalid, for[" + bean.getClass() + "#" + method.getName() + "] .");
                if (!(method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(DtpThreadPoolExecutor.class))) {
                    throw new DtpException("dtp ------> method-Dtphandler param-classtype invalid, for[" + bean.getClass() + "#" + method.getName() + "] , " +
                            "The correct method format like \" public void setThreadPoolExecutor(DtpThreadPoolExecutor threadPoolExecutor) \" .");
                }
                method.setAccessible(true);
                dtpInjectorContainer.setDtpInjector(name, new DtpMethodInjector(bean, method, dtp));
            }
        }

        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            boolean annotationPresent = declaredField.isAnnotationPresent(Dtp.class);
            if (annotationPresent) {
                declaredField.setAccessible(true);
                Dtp dtp = declaredField.getAnnotation(Dtp.class);
                if (Objects.isNull(dtp)) {
                    continue;
                }
                String name = getName(dtp, "dtp ------> field-Dtphandler name invalid, for[" + bean.getClass() + "#" + declaredField + "] .");
                dtpInjectorContainer.setDtpInjector(name, new DtpFiledInjector(bean, declaredField, dtp));
            }
        }
        return bean;
    }

    private String getName(Dtp dtp, String errorLog) {
        String name = dtp.name();
        if (name.trim().length() == 0) {
            throw new RuntimeException(errorLog);
        }
        return name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        dtpInjectorContainer = applicationContext.getBean(DtpInjectorContainer.class);
    }
}
