package com.jay.spring.service.v6;

import com.jay.spring.stereotype.Component;
import com.jay.spring.util.MessageTracker;

/**
 * Created by xiang.wei on 2018/9/8
 *
 * @author xiang.wei
 */
@Component(value = "petStoreService")
public class PetStoreService implements IPetStoreService {
    public PetStoreService() {
    }

    @Override
    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }
}
