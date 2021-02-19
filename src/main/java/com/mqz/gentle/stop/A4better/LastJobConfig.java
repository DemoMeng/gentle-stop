package com.mqz.gentle.stop.A4better;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author mqz
 * @description
 * @abount https://github.com/DemoMeng
 * @since 2021/2/19
 */
@Configuration
public class LastJobConfig {

    private final static Logger log = LoggerFactory.getLogger(LastJobConfig.class);

    @PreDestroy
    public void lastJob(){
        log.info("程序关闭前，执行数据备份操作。。。。");
    }

}
