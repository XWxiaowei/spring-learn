package com.jay.spring.tx;

import com.jay.spring.util.MessageTracker;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
public class TransactionManager {
    public void start(){
        System.out.println("start tx");
        MessageTracker.addMsg("start tx");
    }
    public void commit(){
        System.out.println("commit tx");
        MessageTracker.addMsg("commit tx");
    }
    public void rollback(){
        System.out.println("rollback tx");
        MessageTracker.addMsg("rollback tx");
    }
}
