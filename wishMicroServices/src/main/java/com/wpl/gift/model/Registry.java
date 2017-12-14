package com.wpl.gift.model;

import java.util.List;

/**
 * Author: Manohar
 */
public class Registry {
    private int id;
    private String name;
    private int userId;
    private String shared;
    private List<RegistryItem> registryItems;
    private List<Integer> userIds;
    private String userName;

    public Registry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public List<RegistryItem> getRegistryItems() {
        return registryItems;
    }

    public void setRegistryItems(List<RegistryItem> registryItems) {
        this.registryItems = registryItems;
    }


    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
