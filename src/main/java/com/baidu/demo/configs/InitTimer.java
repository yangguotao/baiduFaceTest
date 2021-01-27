package com.baidu.demo.configs;

import com.baidu.demo.utils.BaiDuAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yangguotao
 * @Date: 2019/12/18
 * @Version 1.0
 */
@Slf4j
@Component
public class InitTimer {
    @PostConstruct
    public void InitUpdataBaiduACCESS_TOKEN() {
        try {
            //TODO 重写线程获取方式
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
            scheduledExecutorService.scheduleAtFixedRate(BaiDuAuth::getAccessToken, 0, 28, TimeUnit.DAYS);
        } catch (Exception e) {
            log.error("{}:更新ACCESSTOKEN异常", InitTimer.class);
        }
    }
}
