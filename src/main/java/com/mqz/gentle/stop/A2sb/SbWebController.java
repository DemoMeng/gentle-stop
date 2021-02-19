package com.mqz.gentle.stop.A2sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2021/2/19
 */
@RestController
@RequestMapping("/sb")
public class SbWebController implements ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(SbWebController.class);

    @GetMapping(value = "/start")
    public String start(){
        long start = System.currentTimeMillis();
        log.info("springboot-收到请求--开始--start");
        try {
            log.info("springboot-模拟业务请求-start");
            Thread.sleep(20000);
            log.info("springboot-模拟业务请求-end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("springboot-请求处理--完成--end");
        long end = System.currentTimeMillis()-start;
        return String.format("springboot-共计耗时：%d",end);
    }


    @GetMapping(value = "shutdown")
    public void shutdown(){
        ConfigurableApplicationContext cac = (ConfigurableApplicationContext) context;
        cac.close();
    }


    private ApplicationContext context;
    /**
     * 实现ApplicationContextAware需要实现的接口
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
