package com.mqz.gentle.stop.A3actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/actuator")
public class ActuatorController{

    private final static Logger log = LoggerFactory.getLogger(ActuatorController.class);

    @GetMapping(value = "/start")
    public String start(){
        long start = System.currentTimeMillis();
        log.info("actuator-收到请求--开始--start");
        try {
            log.info("actuator-模拟业务请求-start");
            Thread.sleep(20000);
            log.info("actuator-模拟业务请求-end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("actuator-请求处理--完成--end");
        long end = System.currentTimeMillis()-start;
        return String.format("actuator-共计耗时：%d",end);
    }


    /**
     * 需要停止程序，需要请求 localhost:8080/actuator/shutdown
     * - 这是actuator提供的优雅停机的接口，也就是在application.yml配置management
     * - 该接口为POST方式
     */

}
