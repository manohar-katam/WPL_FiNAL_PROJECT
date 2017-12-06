package com.wpl.gift.service;

import com.wpl.gift.model.Inventory;
import com.wpl.gift.model.Product;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Author Manohar
 */
@Component
public interface InventoryService {

    boolean addInventory(Inventory inventory);

    List<Product> viewInventory() throws SQLException;

    Product getProduct(int id);


    boolean deleteProduct(int id);

}
