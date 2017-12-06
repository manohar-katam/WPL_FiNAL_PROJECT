package com.wpl.gift.model;

import java.util.List;

/**
 * Author Sneha
 */
public class UserListModel {
    public Response response;
    public List<User> users;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
