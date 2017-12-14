package com.wpl.gift.model;

import java.util.List;
/**
 *  Author Sneha, Manohar
 */
public class RegistryListModel {
    private Response response;
    private List<Registry> registryList;

    public RegistryListModel() {
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<Registry> getRegistryList() {
        return registryList;
    }

    public void setRegistryList(List<Registry> registryList) {
        this.registryList = registryList;
    }
}
