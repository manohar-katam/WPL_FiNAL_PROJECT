package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;

import com.wpl.gift.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


/**
 * Author Manohar
 */
@Controller
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @RequestMapping(value = Route.inventoryPage, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response response(@RequestBody Inventory inventory) {
        Response response;
        if(inventoryService.addInventory(inventory)) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.creationCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        return response;
    }

    @RequestMapping(value = Route.viewDeletePage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Response response(@RequestParam(value="id") int id ) {
        Response response;
        if(inventoryService.deleteProduct(id)) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        return response;
    }

    @RequestMapping(value = Route.viewInventoryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody InventoryModel inventoryModel() throws SQLException {
        InventoryModel inventoryModel = new InventoryModel();

        Response response = new Response();
        List<Product> list = inventoryService.viewInventory();

        if(list!= null) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        inventoryModel.setInventoryList(list);
        inventoryModel.setResponse(response);
        return inventoryModel ;
    }


    @RequestMapping(value = Route.getProduct, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ProductModel inventoryModel(@RequestParam("id") int id) throws SQLException {
        ProductModel productModel = new ProductModel();

        Response response = new Response();
        Product product = inventoryService.getProduct(id);

        if(product!= null) {
            response = new Response(ConstantMessages.foundResult,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        productModel.setProduct(product);
        productModel.setResponse(response);
        return productModel ;
    }
}
