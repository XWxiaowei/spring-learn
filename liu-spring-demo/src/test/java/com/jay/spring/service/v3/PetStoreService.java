package com.jay.spring.service.v3;

import com.jay.spring.dao.v3.AccountDao;
import com.jay.spring.dao.v3.ItemDao;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:42
 */
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;

    public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }

    public PetStoreService(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }


    public int getVersion() {
        return version;
    }
}
