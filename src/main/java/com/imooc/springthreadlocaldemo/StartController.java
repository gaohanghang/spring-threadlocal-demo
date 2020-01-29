package com.imooc.springthreadlocaldemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2020-01-29 23:21
 **/
@RestController
public class StartController {

    static ThreadLocal<Integer> c = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    synchronized void __add() throws InterruptedException {
        Thread.sleep(100);
        c.set(c.get() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat() {
        return c.get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        //Thread.sleep(100);
        //c++;
        __add();
        return 1;
    }

}
