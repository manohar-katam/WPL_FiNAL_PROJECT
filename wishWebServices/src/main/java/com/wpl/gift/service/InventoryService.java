package com.wpl.gift.service;

import com.wpl.gift.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Author Manohar
 */
@Component
public interface InventoryService {

    Response addInventory(Inventory inventory);

    InventoryModel viewInventory();

    Response deleteProduct(@RequestParam(value = "id") int id);

    ProductModel getProduct(int id);

}
