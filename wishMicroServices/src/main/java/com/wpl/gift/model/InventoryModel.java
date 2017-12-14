package com.wpl.gift.model;

import java.util.List;
/**
 * Author: Manohar
 */
public class InventoryModel {

    private Response response;
    private List<Product> inventoryList;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Product> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Product> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
