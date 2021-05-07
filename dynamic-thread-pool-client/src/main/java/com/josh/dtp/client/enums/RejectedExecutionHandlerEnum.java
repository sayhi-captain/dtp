package com.josh.dtp.client.enums;

import com.josh.dtp.client.concurrent.DtpRejectedExecutionHandler;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;
import com.josh.dtp.client.model.DtpException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 拒绝策略枚举
 *
 * @author : Josh
 */
@Getter
public enum RejectedExecutionHandlerEnum {

    /**
     * A handler for rejected tasks that throws a
     */
    abortPolicy("AbortPolicy", new DtpThreadPoolExecutor.AbortPolicy()),

    /**
     * A handler for rejected tasks that runs the rejected task
     * directly in the calling thread of the {@code execute} method,
     * unless the executor has been shut down, in which case the task
     * is discarded.
     */
    callerRunsPolicy("CallerRunsPolicy", new DtpThreadPoolExecutor.CallerRunsPolicy()),

    /**
     * A handler for rejected tasks that silently discards the
     * rejected task.
     */
    discardPolicy("DiscardPolicy", new DtpThreadPoolExecutor.DiscardPolicy()),

    /**
     * A handler for rejected tasks that discards the oldest unhandled
     * request and then retries {@code execute}, unless the executor
     * is shut down, in which case the task is discarded.
     */
    discardOldestPolicy("DiscardOldestPolicy", new DtpThreadPoolExecutor.DiscardOldestPolicy());

    private static final Map<String, RejectedExecutionHandlerEnum> ENUM_MAP = new HashMap<>(RejectedExecutionHandlerEnum.values().length);

    static {
        for (RejectedExecutionHandlerEnum e : RejectedExecutionHandlerEnum.values()) {
            ENUM_MAP.put(e.getRejectedExecutionHandlerName(), e);
        }
    }

    private String rejectedExecutionHandlerName;
    private DtpRejectedExecutionHandler dtpRejectedExecutionHandler;

    RejectedExecutionHandlerEnum(String rejectedExecutionHandlerName, DtpRejectedExecutionHandler dtpRejectedExecutionHandler) {
        this.rejectedExecutionHandlerName = rejectedExecutionHandlerName;
        this.dtpRejectedExecutionHandler = dtpRejectedExecutionHandler;
    }

    /**
     * 根据 {@queueName rejectedExecutionHandlerName} 获取枚举类
     *
     * @param rejectedExecutionHandlerName 枚举类的值
     * @return 枚举类
     */
    public static RejectedExecutionHandlerEnum getByRejectedExecutionHandlerName(String rejectedExecutionHandlerName) {
        return ENUM_MAP.get(rejectedExecutionHandlerName);
    }

    public static List<String> getAllRejectedExecutionHandlerName() {
        Set<String> keySet = ENUM_MAP.keySet();
        return new ArrayList<>(keySet);
    }

    public static DtpRejectedExecutionHandler getRejectedExecutionHandler(String rejectedExecutionHandlerName) {
        RejectedExecutionHandlerEnum rejectedExecutionHandlerEnum = ENUM_MAP.get(rejectedExecutionHandlerName);
        if (rejectedExecutionHandlerEnum == null) {
            throw new DtpException("dtp ------> [" + rejectedExecutionHandlerName + "] RejectedExecutionHandlerName configuration errors. ");
        }
        return rejectedExecutionHandlerEnum.dtpRejectedExecutionHandler;
    }
}
