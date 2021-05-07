package com.josh.dtp.client.support.factory;

import com.josh.dtp.client.concurrent.ResizableCapacityLinkedBlockingDeque;
import com.josh.dtp.client.concurrent.ResizableCapacityLinkedBlockingQueue;
import com.josh.dtp.client.enums.DtpQueueEnum;
import com.josh.dtp.client.model.DtpException;
import com.josh.dtp.client.model.DtpQueueDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 描述: 队列工厂类
 *
 * @author Josh
 * @date 2021/4/25 9:06 下午
 */
@Slf4j
public class DtpQueueFactory implements DisposableBean {

    private static DtpQueueFactory dtpQueueFactory = new DtpQueueFactory();
    /**
     * key:线程池名称 value:线程池队列
     */
    private ConcurrentMap<String, BlockingQueue<Runnable>> queueConcurrentMap = new ConcurrentHashMap<>();

    public static DtpQueueFactory getInstance() {
        return dtpQueueFactory;
    }

    public static void refreshInstance() {
        dtpQueueFactory = new DtpQueueFactory();
    }

    /**
     * 根据线程池名称获取阻塞队列
     *
     * @param poolName 线程池名称
     * @return 阻塞队列
     */
    public BlockingQueue<Runnable> getQueue(String poolName) {
        return queueConcurrentMap.get(poolName);
    }

    /**
     * 获取队列 不存在则创建队列
     *
     * @param dtpQueueDTO
     * @return
     */
    public BlockingQueue<Runnable> getQueue(DtpQueueDTO dtpQueueDTO) {
        String poolName = dtpQueueDTO.getPoolName();
        BlockingQueue<Runnable> queue = getQueue(poolName);
        if (Objects.nonNull(queue)) {
            return queue;
        }
        DtpQueueEnum queueEnum = dtpQueueDTO.getQueueEnum();
        Integer queueCapacity = dtpQueueDTO.getQueueCapacity();
        BlockingQueue<Runnable> blockingQueue = createQueue(queueEnum, queueCapacity);
        queueConcurrentMap.put(poolName, blockingQueue);
        return blockingQueue;
    }

    /**
     * 修改队列容量
     *
     * @param dtpQueueDTO
     */
    public void changeQueueCapacity(DtpQueueDTO dtpQueueDTO) {
        String poolName = dtpQueueDTO.getPoolName();
        Integer queueCapacity = dtpQueueDTO.getQueueCapacity();
        BlockingQueue<Runnable> blockingQueue = queueConcurrentMap.get(poolName);
        if (Objects.isNull(blockingQueue)) {
            log.error("dtp ------> threadPool {} is not exists.", poolName);
            return;
        }
        if ((blockingQueue.size() + blockingQueue.remainingCapacity()) != queueCapacity) {
            if (blockingQueue instanceof ResizableCapacityLinkedBlockingQueue) {
                ResizableCapacityLinkedBlockingQueue<Runnable> queue = (ResizableCapacityLinkedBlockingQueue<Runnable>) blockingQueue;
                queue.setCapacity(queueCapacity);
            } else if (blockingQueue instanceof ResizableCapacityLinkedBlockingDeque) {
                ResizableCapacityLinkedBlockingDeque<Runnable> queue = (ResizableCapacityLinkedBlockingDeque<Runnable>) blockingQueue;
                queue.setCapacity(queueCapacity);
            } else if (blockingQueue instanceof LinkedBlockingQueue) {
                log.error("dtp ------>  Dynamic resizing of LinkedBlockingQueue is not supported .");
            } else if (blockingQueue instanceof ArrayBlockingQueue) {
                log.error("dtp ------>  Dynamic resizing of ArrayBlockingQueue is not supported .");
            } else if (blockingQueue instanceof LinkedBlockingDeque) {
                log.error("dtp ------>  Dynamic resizing of LinkedBlockingDeque is not supported .");
            } else if (blockingQueue instanceof PriorityBlockingQueue) {
                log.error("dtp ------>  Dynamic resizing of PriorityBlockingQueue is not supported .");
            } else if (blockingQueue instanceof SynchronousQueue) {
                log.error("dtp ------>  Dynamic resizing of SynchronousQueue is not supported .");
            } else if (blockingQueue instanceof LinkedTransferQueue) {
                log.error("dtp ------>  Dynamic resizing of LinkedTransferQueue is not supported .");
            } else {
                log.error("dtp ------>  Incorrect Queue configuration .");
            }
        }
    }

    /**
     * 创建队列
     *
     * @param queueEnum     队列枚举
     * @param queueCapacity 队列容量
     * @return
     */
    private BlockingQueue<Runnable> createQueue(DtpQueueEnum queueEnum, Integer queueCapacity) {
        switch (queueEnum) {
            case resizableCapacityLinkedBlockIngQueue:
                return new ResizableCapacityLinkedBlockingQueue<>(queueCapacity);
            case resizableCapacityLinkedBlockingDeque:
                return new ResizableCapacityLinkedBlockingDeque<>(queueCapacity);
            case linkedBlockingQueue:
                return new LinkedBlockingQueue<>(queueCapacity);
            case linkedBlockingDeque:
                return new LinkedBlockingDeque<>(queueCapacity);
            case linkedTransferQueue:
                return new LinkedTransferQueue<>();
            case arrayBlockingQueue:
                return new ArrayBlockingQueue<>(queueCapacity);
            case priorityBlockingQueue:
                return new PriorityBlockingQueue<>(queueCapacity);
            case synchronousQueue:
                return new SynchronousQueue<>();
            default:
                throw new DtpException("dtp ------> Queue configuration error. ");
        }
    }

    @Override
    public void destroy() throws Exception {

    }
}
