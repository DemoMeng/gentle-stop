package com.mqz.gentle.stop.A4better;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2021/2/19
 */
public class GentleStopConfig implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;
    //延迟15秒关闭程序，为了是Thread.sleep(20000)中的，目的是为了模拟业务执行场景
    private final int waitTime = 15;


    /**
     * tomcat
     * @param connector
     */
    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    /**
     * ApplicationListener
     * @param contextClosedEvent
     */
    @Override

    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        connector.pause();
        Executor executor = connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
                    System.out.println("请尝试暴力关闭");
                }
            } catch (InterruptedException ex) {
                System.out.println("异常了");
                Thread.currentThread().interrupt();
            }
        }
    }
}
