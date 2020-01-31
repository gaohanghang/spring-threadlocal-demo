package com.imooc.springthreadlocaldemo;

/**
 * @Description
 * @Author Gao Hang Hang
 * @Date 2020-01-30 00:49
 **/
public class Val<T> {

    T v;

    public void set(T _v) {
        v = _v;
    }

    public T get() {
        return v;
    }

}
