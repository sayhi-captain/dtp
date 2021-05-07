package com.josh.dtp.client.annotation.impl;

import com.josh.dtp.client.annotation.Dtp;
import com.josh.dtp.client.annotation.DtpInjector;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;

import java.lang.reflect.Method;

/**
 * 描述: set方法上的注解注入类
 *
 * @author Josh
 * @date 2021/4/25 7:03 下午
 */
public class DtpMethodInjector extends DtpInjector {

    private final Object target;
    private final Method method;

    public DtpMethodInjector(Object target, Method method, Dtp dtp) {
        this.target = target;
        this.method = method;
        this.dtp = dtp;
    }

    @Override
    public boolean inject(DtpThreadPoolExecutor threadPool) {
        try {
            method.invoke(target, threadPool);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
