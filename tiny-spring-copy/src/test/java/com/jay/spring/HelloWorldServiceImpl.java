package com.jay.spring;

/**
 * Created by xiang.wei on 2018/6/4
 *
 * @author xiang.wei
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    private String text;
    private OutputService outputService;


    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
