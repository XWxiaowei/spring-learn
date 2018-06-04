package com.jay.spring;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class HelloWorldService {
    private String text;

    public void helloWorld() {
        System.out.println("hello world!");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
