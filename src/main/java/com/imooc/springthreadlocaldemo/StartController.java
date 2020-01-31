package com.imooc.springthreadlocaldemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2020-01-29 23:21
 **/
@RestController
public class StartController {

    static HashSet<Val<Integer>> set = new HashSet<>();

    synchronized static void addSet(Val<Integer> v) {
        set.add(v);
    }

    /**
     * 函数式方法
     */
    static ThreadLocal<Val<Integer>> c = ThreadLocal.withInitial(() -> {
                Val<Integer> v = new Val<>();
                v.set(0);
                addSet(v);
                return v;
    });

    //static ThreadLocal<Val<Integer>> c = new ThreadLocal<Val<Integer>>() {
    //    @Override
    //    protected Val<Integer> initialValue() {
    //        Val<Integer> v = new Val<>();
    //        v.set(0);
    //        addSet(v);
    //        return v;
    //    }
    //};

    void __add() throws InterruptedException {
        Thread.sleep(100);
        Val<Integer> v = c.get();
        v.set(v.get() + 1);
    }

    @RequestMapping("/stat")
    public Integer stat() {
        System.out.println(set.size());
        for (Val<Integer> integerVal : set) {
            System.out.println(integerVal.get());
        }
        return set.stream().map(x -> x.get()).reduce((a, x) -> a + x).get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        __add();
        return 1;
    }

}
