package com.josh.dtp.client.threadpool;

import com.josh.dtp.client.TestCaseApplication;
import com.josh.dtp.client.annotation.Dtp;
import com.josh.dtp.client.concurrent.DtpThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author Josh
 * @date 2021/4/26 2:46 下午
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCaseApplication.class)
public class ThreadInitTestCase {
    @Dtp(name = "dtp-test1")
    private DtpThreadPoolExecutor dtpTest1;

    @Test
    public void test1() {
        log.info("corePoolSize: {}, maximumPoolSize: {}, workQueue: {}, RejectedExecutionHandler: {}", dtpTest1.getCorePoolSize(), dtpTest1.getMaximumPoolSize(), dtpTest1.getQueue(), dtpTest1.getDtpRejectedExecutionHandler());
        dtpTest1.execute(() -> {
            log.info("thread running...");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(10L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("activeCount: {}", dtpTest1.getActiveCount());
        dtpTest1.shutdown();
        while (true) {
            if (dtpTest1.isTerminated()) {
                break;
            }
        }
        log.info("activeCount: {}", dtpTest1.getActiveCount());
    }
}
