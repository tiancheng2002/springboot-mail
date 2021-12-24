package com.zhu.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    //秒 分 时 日 月 星期     *表示全部的值，?表示不确定的值，
    @Scheduled(cron = "*/5 * * * * ?")
    public void Hello(){
        System.out.println("定时任务被执行了");
    }
}
