package com.mqz.gentle.stop.A1killPid;

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
@RequestMapping("/kill/15")
public class WebController {

    private final static Logger log = LoggerFactory.getLogger(WebController.class);

    @GetMapping(value = "/start")
    public String start(){
        long start = System.currentTimeMillis();
        log.info("kill-收到请求--开始--start");
        try {
            log.info("kill-模拟业务请求-start");
            Thread.sleep(20000);
            log.info("kill-模拟业务请求-end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("kill-请求处理--完成--end");
        long end = System.currentTimeMillis()-start;
        return String.format("kill-共计耗时：%d",end);
    }

}
