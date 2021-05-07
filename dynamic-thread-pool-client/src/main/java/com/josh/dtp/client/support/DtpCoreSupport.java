package com.josh.dtp.client.support;

import com.josh.dtp.client.annotation.Dtp;
import com.josh.dtp.client.annotation.DtpInjector;
import com.josh.dtp.client.annotation.DtpInjectorContainer;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;
import com.josh.dtp.client.configuration.DtpConfigProperties;
import com.josh.dtp.client.model.DtpThreadPoolDTO;
import com.josh.dtp.client.support.factory.DtpQueueFactory;
import com.josh.dtp.client.support.factory.DtpThreadPoolFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述: dtp动态线程池核心类
 * 拉取服务端列表
 * 拉取配置初始化
 * 无配置项 - 初始化所有线程池注入
 * 注册客户端所有线程池到后台管理端
 * 监听线程池配置变更
 *
 * @author Josh
 * @date 2021/4/25 9:01 下午
 */
@Slf4j
public class DtpCoreSupport implements SmartInitializingSingleton, ApplicationContextAware, DisposableBean {

    private static final String STRING_COMMA = ",";
    /**
     * 线程池工厂
     */
    private final DtpThreadPoolFactory dtpThreadPoolFactory = DtpThreadPoolFactory.getInstance();
    private ApplicationContext applicationContext;
    /**
     * 配置类
     */
    private DtpConfigProperties dtpConfigProperties;
    /**
     * dtp线程池反射注入类容器
     */
    private DtpInjectorContainer dtpInjectorContainer;

    @Override
    public void afterSingletonsInstantiated() {
        //初始化队列工厂
        DtpQueueFactory.refreshInstance();
        //初始化线程池工厂
        DtpThreadPoolFactory.refreshInstance();
        //启动
        start();
    }

    /**
     * 启动
     * <p>
     * 1. 初始化服务端列表
     * 2. 包装线程池注册信息
     * 3. 向服务端发起注册
     * 4. 拉取服务端配置, 并初始化线程池
     * 5. 初始化线程池
     * 6. 开启服务端配置变更监听
     */
    public void start() {
        //初始化服务端列表
        initAdminList();
        //todo 包装线程池注册信息

        //todo 向服务端发起注册

        //todo 拉取服务端配置, 并初始化线程池

        //初始化线程池注入
        initDtpInjectors();

        //todo 开启服务端配置变更监听

    }

    /**
     * 初始化服务端列表
     */
    private void initAdminList() {
        String adminUrl = dtpConfigProperties.getAdminUrl();
        if (StringUtils.isNotBlank(adminUrl)) {
            String[] split = adminUrl.trim().split(STRING_COMMA);
            for (String address : split) {
                if (StringUtils.isNotBlank(address)) {
                    //todo admin列表逻辑待完善
                }
            }
        }
    }

    /**
     * 将线程池对象反射注入到方法|变量上
     */
    private void initDtpInjectors() {
        Map<String, List<DtpInjector>> dtpInjectorMap = dtpInjectorContainer.getDtpInjectorMap();
        for (Map.Entry<String, List<DtpInjector>> dtpInjectorEntry : dtpInjectorMap.entrySet()) {
            String poolName = dtpInjectorEntry.getKey();
            //一个线程池 存在多处使用 变量或方法 的注入
            List<DtpInjector> dtpInjectors = dtpInjectorEntry.getValue();
            if (CollectionUtils.isEmpty(dtpInjectors)) {
                continue;
            }
            DtpThreadPoolExecutor dtpThreadPoolExecutor = dtpThreadPoolFactory.getExecutor(poolName);
            if (Objects.isNull(dtpThreadPoolExecutor)) {
                //取收集到的第一个参数 问题点: A线程池 先配置了默认的, 在另一个地方配置了具体信息就会只取第一个; 因为key是线程池名称
                DtpInjector dtpInjector = dtpInjectors.get(0);
                Dtp dtp = dtpInjector.getDtp();
                dtpThreadPoolExecutor = loadDefaultDtpThreadPool(dtp);
                log.info("dtp ------> {} No configuration Dtp.", dtp.name());
            }
            //注入线程池对象
            for (DtpInjector dtpInjector : dtpInjectors) {
                dtpInjector.inject(dtpThreadPoolExecutor);
            }
        }
    }

    /**
     * 初始化默认线程池对象
     *
     * @param dtp
     * @return
     */
    private DtpThreadPoolExecutor loadDefaultDtpThreadPool(Dtp dtp) {
        return dtpThreadPoolFactory.getExecutor(new DtpThreadPoolDTO()
                .setPoolName(dtp.name())
                .setCorePoolSize(dtp.corePoolSize())
                .setMaximumPoolSize(dtp.maximumPoolSize())
                .setKeepAliveSeconds(dtp.keepAliveSeconds())
                .setRejectedExecutionHandlerEnum(dtp.rejectedExecutionHandlerEnum())
                .setQueueEnum(dtp.queueEnum())
                .setQueueCapacity(dtp.queueCapacity())
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.dtpInjectorContainer = applicationContext.getBean(DtpInjectorContainer.class);
        this.dtpConfigProperties = applicationContext.getBean(DtpConfigProperties.class);
    }

    @Override
    public void destroy() throws Exception {

    }
}
