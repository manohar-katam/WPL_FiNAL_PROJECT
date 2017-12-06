package com.wpl.gift.model;

public class ItemRegistry {
    private int id;
    private int reg_item_id;
    private String regname;
    private int user_id;
    private int product_id;
    private int assignee_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReg_item_id() {
        return reg_item_id;
    }

    public void setReg_item_id(int reg_item_id) {
        this.reg_item_id = reg_item_id;
    }

    public String getRegname() {
        return regname;
    }

    public void setRegname(String regname) {
        this.regname = regname;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }}
