package com.josh.dtp.client.support.factory;

import com.josh.dtp.client.concurrent.DtpRejectedExecutionHandler;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;
import com.josh.dtp.client.enums.DtpQueueEnum;
import com.josh.dtp.client.enums.RejectedExecutionHandlerEnum;
import com.josh.dtp.client.model.DtpQueueDTO;
import com.josh.dtp.client.model.DtpThreadPoolDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 线程池工厂类
 *
 * @author Josh
 * @date 2021/4/25 9:07 下午
 */
@Slf4j
public class DtpThreadPoolFactory implements DisposableBean {
    private static DtpThreadPoolFactory dtpThreadPoolFactory = new DtpThreadPoolFactory();
    /**
     * key:线程池名称 value:线程池
     */
    private final ConcurrentMap<String, DtpThreadPoolExecutor> threadPoolExecutorMap = new ConcurrentHashMap<>();
    /**
     * 线程池队列工厂
     */
    private final DtpQueueFactory dtpQueueFactory = DtpQueueFactory.getInstance();

    public static DtpThreadPoolFactory getInstance() {
        return dtpThreadPoolFactory;
    }

    public static void refreshInstance() {
        dtpThreadPoolFactory = new DtpThreadPoolFactory();
    }

    /**
     * 获取线程池对象
     *
     * @param poolName
     * @return
     */
    public DtpThreadPoolExecutor getExecutor(String poolName) {
        return threadPoolExecutorMap.get(poolName);
    }

    /**
     * 获取线程池对象
     *
     * @param dtpThreadPoolDTO
     * @return
     */
    public DtpThreadPoolExecutor getExecutor(DtpThreadPoolDTO dtpThreadPoolDTO) {
        String poolName = dtpThreadPoolDTO.getPoolName();
        DtpThreadPoolExecutor executor = getExecutor(poolName);
        if (Objects.nonNull(executor)) {
            return executor;
        }

        Integer corePoolSize = dtpThreadPoolDTO.getCorePoolSize();
        Integer maximumPoolSize = dtpThreadPoolDTO.getMaximumPoolSize();
        Long keepAliveSeconds = dtpThreadPoolDTO.getKeepAliveSeconds();
        RejectedExecutionHandlerEnum rejectedExecutionHandlerEnum = dtpThreadPoolDTO.getRejectedExecutionHandlerEnum();

        //构建队列
        DtpQueueEnum queueEnum = dtpThreadPoolDTO.getQueueEnum();
        Integer queueCapacity = dtpThreadPoolDTO.getQueueCapacity();
        BlockingQueue<Runnable> dtpQueue = dtpQueueFactory.getQueue(new DtpQueueDTO()
                .setPoolName(poolName)
                .setQueueEnum(queueEnum)
                .setQueueCapacity(queueCapacity)
        );

        //构建线程池
        DtpThreadPoolExecutor threadPooExecutor = new DtpThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS,
                dtpQueue,
                r -> new Thread(r, poolName + "-" + r.hashCode()),
                rejectedExecutionHandlerEnum.getDtpRejectedExecutionHandler());
        threadPoolExecutorMap.put(poolName, threadPooExecutor);
        return threadPooExecutor;
    }

    /**
     * 更新线程池参数
     *
     * @param dtpThreadPoolDTO
     */
    private void changeThreadPool(DtpThreadPoolDTO dtpThreadPoolDTO) {
        String poolName = dtpThreadPoolDTO.getPoolName();
        DtpThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(poolName);
        if (Objects.isNull(threadPoolExecutor)) {
            log.error("dtp ------> threadPool {} is not exists.", poolName);
            return;
        }
        Integer corePoolSize = dtpThreadPoolDTO.getCorePoolSize();
        if (Objects.nonNull(corePoolSize) && threadPoolExecutor.getCorePoolSize() != corePoolSize) {
            threadPoolExecutor.setCorePoolSize(corePoolSize);
        }
        Integer maximumPoolSize = dtpThreadPoolDTO.getMaximumPoolSize();
        if (Objects.nonNull(maximumPoolSize) && threadPoolExecutor.getMaximumPoolSize() != maximumPoolSize) {
            threadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
        }
        Long keepAliveSeconds = dtpThreadPoolDTO.getKeepAliveSeconds();
        if (Objects.nonNull(keepAliveSeconds) && threadPoolExecutor.getKeepAliveTime(TimeUnit.SECONDS) != keepAliveSeconds) {
            threadPoolExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
        }
        Integer queueCapacity = dtpThreadPoolDTO.getQueueCapacity();
        DtpQueueEnum queueEnum = dtpThreadPoolDTO.getQueueEnum();
        if (Objects.nonNull(queueCapacity) && Objects.nonNull(queueEnum)) {
            dtpQueueFactory.changeQueueCapacity(new DtpQueueDTO()
                    .setPoolName(poolName)
                    .setQueueEnum(queueEnum)
                    .setQueueCapacity(queueCapacity));
        }
        RejectedExecutionHandlerEnum rejectedExecutionHandlerEnum = dtpThreadPoolDTO.getRejectedExecutionHandlerEnum();
        if (Objects.nonNull(rejectedExecutionHandlerEnum)) {
            DtpRejectedExecutionHandler dtpRejectedExecutionHandler = rejectedExecutionHandlerEnum.getDtpRejectedExecutionHandler();
            if (!threadPoolExecutor.getDtpRejectedExecutionHandler().getClass().equals(dtpRejectedExecutionHandler.getClass())) {
                threadPoolExecutor.setDtpRejectedExecutionHandler(dtpRejectedExecutionHandler);
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        for (DtpThreadPoolExecutor threadPoolExecutor : threadPoolExecutorMap.values()) {
            threadPoolExecutor.shutdown();
        }
    }
}
