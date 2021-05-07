package com.josh.dtp.client.enums;

import com.josh.dtp.client.concurrent.ResizableCapacityLinkedBlockingDeque;
import com.josh.dtp.client.concurrent.ResizableCapacityLinkedBlockingQueue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 队列枚举
 *
 * @author Josh
 */
@Getter
public enum DtpQueueEnum {

    /**
     * Dynamically modifiable {@link LinkedBlockingQueue}
     */
    resizableCapacityLinkedBlockIngQueue("ResizableCapacityLinkedBlockingQueue", ResizableCapacityLinkedBlockingQueue.class),

    /**
     * Dynamically modifiable {@link LinkedBlockingDeque}
     */
    resizableCapacityLinkedBlockingDeque("ResizableCapacityLinkedBlockingDeque", ResizableCapacityLinkedBlockingDeque.class),

    /**
     * details {@link LinkedBlockingQueue}
     */
    linkedBlockingQueue("LinkedBlockingQueue", LinkedBlockingQueue.class),

    /**
     * details {@link LinkedBlockingDeque}
     */
    linkedBlockingDeque("LinkedBlockingDeque", LinkedBlockingDeque.class),

    /**
     * details {@link PriorityBlockingQueue}
     */
    priorityBlockingQueue("PriorityBlockingQueue", PriorityBlockingQueue.class),

    /**
     * details {@link ArrayBlockingQueue}
     */
    arrayBlockingQueue("ArrayBlockingQueue", ArrayBlockingQueue.class),

    /**
     * details {@link SynchronousQueue}
     */
    synchronousQueue("SynchronousQueue", SynchronousQueue.class),

    /**
     * details {@link LinkedTransferQueue}
     */
    linkedTransferQueue("LinkedTransferQueue", LinkedTransferQueue.class);

    private static final Map<String, DtpQueueEnum> ENUM_MAP = new HashMap<>(DtpQueueEnum.values().length);

    static {
        for (DtpQueueEnum e : DtpQueueEnum.values()) {
            ENUM_MAP.put(e.getQueueName(), e);
        }
    }

    /**
     * 队列名称
     */
    private final String queueName;
    /**
     * 队列 class 对象
     */
    private final Class<?> queueClass;

    DtpQueueEnum(String queueName, Class<?> queueClass) {
        this.queueName = queueName;
        this.queueClass = queueClass;
    }

    /**
     * 根据 {@queueName queueName} 获取枚举类
     *
     * @param queueName 枚举类的值
     * @return 枚举类
     */
    public static DtpQueueEnum getByQueueName(String queueName) {
        return ENUM_MAP.get(queueName);
    }

    public static List<String> getAllQueueName() {
        Set<String> keySet = ENUM_MAP.keySet();
        return new ArrayList<>(keySet);
    }

}
