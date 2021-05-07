package com.josh.dtp.client.annotation.impl;

import com.josh.dtp.client.annotation.Dtp;
import com.josh.dtp.client.annotation.DtpInjector;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;

import java.lang.reflect.Field;

/**
 * 描述: 变量上的注解注入类
 *
 * @author Josh
 * @date 2021/4/25 7:03 下午
 */
public class DtpFiledInjector extends DtpInjector {

    private final Object target;
    private final Field field;

    public DtpFiledInjector(Object target, Field field, Dtp dtp) {
        this.target = target;
        this.field = field;
        this.dtp = dtp;
    }

    @Override
    public boolean inject(DtpThreadPoolExecutor threadPool) {
        try {
            field.set(target, threadPool);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
