package com.jay.spring.service.v5;

import com.jay.spring.bean.factory.annotation.Autowired;
import com.jay.spring.dao.v5.AccountDao;
import com.jay.spring.dao.v5.ItemDao;
import com.jay.spring.stereotype.Component;
import com.jay.spring.util.MessageTracker;

/**
 * Created by xiang.wei on 2018/7/30
 *
 * @author xiang.wei
 */
@Component(value = "petStoreService")
public class PetStoreService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    AccountDao accountDao;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");

    }
    public void placeOrderWithException(){
        throw new NullPointerException();
    }
}
