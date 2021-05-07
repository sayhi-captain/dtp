package com.josh.dtp.client.annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: dtp线程池注入类 - 容器类
 *
 * @author Josh
 * @date 2021/4/25 7:14 下午
 */
public class DtpInjectorContainer {
    /**
     * key: 线程池名称 value: 注解处理类集合
     */
    private Map<String, List<DtpInjector>> dtpInjectorMap = new HashMap<>();

    /**
     * 添加线程池注入类
     *
     * @param name        线程池名称
     * @param dtpInjector 处理类
     */
    public void setDtpInjector(String name, DtpInjector dtpInjector) {
        List<DtpInjector> dtpInjectors = dtpInjectorMap.computeIfAbsent(name, k -> new ArrayList<>());
        dtpInjectors.add(dtpInjector);
    }

    /**
     * 根据线程池名称 - 获取dtp线程池注入类列表
     *
     * @param name 线程池名称
     * @return
     */
    public List<DtpInjector> getDtpInjector(String name) {
        return dtpInjectorMap.get(name);
    }

    /**
     * 获取所有线程池注入类
     *
     * @return
     */
    public Map<String, List<DtpInjector>> getDtpInjectorMap() {
        return dtpInjectorMap;
    }
}
