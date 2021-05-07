package com.josh.dtp.client.annotation;

import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;

/**
 * 描述: 线程池对象注入类
 *
 * @author Josh
 * @date 2021/4/25 7:02 下午
 */
public abstract class DtpInjector {
    /**
     * 注解类
     */
    protected Dtp dtp;

    public Dtp getDtp() {
        return dtp;
    }

    /**
     * 反射 注入 注解处理后的线程池对象
     *
     * @param threadPool
     * @return
     */
    public abstract boolean inject(DtpThreadPoolExecutor threadPool);
}
