package com.jay.spring.service.v4;

import com.jay.spring.bean.factory.annotation.Autowired;
import com.jay.spring.dao.v4.AccountDao;
import com.jay.spring.dao.v4.ItemDao;
import com.jay.spring.stereotype.Component;

/**
 * Created by xiang.wei on 2018/7/14
 *
 * @author xiang.wei
 */
@Component(value="petStoreService")
public class PetStoreService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}
