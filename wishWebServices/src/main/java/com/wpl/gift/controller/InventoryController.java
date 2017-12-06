package com.wpl.gift.controller;

import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;

import com.wpl.gift.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author Manohar
 */
@Controller
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @RequestMapping(value = Route.inventoryPage, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response response(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);

    }

    @RequestMapping(value = Route.viewInventoryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody InventoryModel viewInventory(){

        return inventoryService.viewInventory();
    }

    @RequestMapping(value = Route.viewDeletePage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Response response(@RequestParam(value="id") int id){
        return inventoryService.deleteProduct(id);
    }

    @RequestMapping(value = Route.getProduct, method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody
    ProductModel getProduct(@RequestParam("id") int id) {
        return inventoryService.getProduct(id);
    }
}