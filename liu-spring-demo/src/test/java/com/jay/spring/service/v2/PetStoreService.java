package com.jay.spring.service.v2;

import com.jay.spring.dao.v2.AccountDao;
import com.jay.spring.dao.v2.ItemDao;

/**
 * @author xiang.wei
 * @create 2018/6/11 14:42
 */
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;
    private int version;


    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public String getOwner() {
        return owner;
    }

    public int getVersion() {
        return version;
    }
}
