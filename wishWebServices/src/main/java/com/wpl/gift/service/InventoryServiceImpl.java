package com.wpl.gift.service;

import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
/**
 * Author Manohar
 */
@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService{


    @Override
    public Response addInventory(Inventory inventory) {
        Response response = new Response();
        RestTemplate restTemplate = new RestTemplate();
        response = restTemplate.postForObject(Route.basePath + Route.addInventory, inventory, Response.class);

        return response;
    }

    @Override
    public InventoryModel viewInventory() {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewInventory);

        InventoryModel result = restTemplate.getForObject(builder.build().encode().toUri(), InventoryModel.class);

        return result;
    }

    @Override
    public Response deleteProduct(@RequestParam(value="id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.deleteProduct);
        builder.queryParam("id", id);
        Response response = restTemplate.getForObject(builder.build().encode().toUri(), Response.class);

        return response;
    }


    @Override
    public ProductModel getProduct(int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getProductCall);

        builder.queryParam("id",id);
        ProductModel result = restTemplate.getForObject(builder.build().encode().toUri(), ProductModel.class);

        return result;
    }

}
