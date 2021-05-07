package com.josh.dtp.client.concurrent;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author : Josh
 * @date : 2020-08-05 16:32
 * copy JDK1.8 RejectedExecutionHandler to modify.
 */
public interface DtpRejectedExecutionHandler {

    /**
     * Method that may be invoked by a {@link DtpThreadPoolExecutor} when
     * {@link DtpThreadPoolExecutor#execute execute} cannot accept a
     * task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon
     * shutdown of the Executor.
     *
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link DtpRejectedExecutionHandler}, which will be
     * propagated to the caller of {@code execute}.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws RejectedExecutionException if there is no remedy
     */
    void rejectedExecution(Runnable r, DtpThreadPoolExecutor executor);
}
