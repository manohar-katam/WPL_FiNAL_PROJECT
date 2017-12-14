package com.wpl.gift.dao;

import com.wpl.gift.model.Inventory;
import com.wpl.gift.model.Product;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
/**
 * Author: Manohar
 */
@Component
public interface InventoryDao {
    boolean addInventory(Inventory inventory);

    boolean deleteProduct(int id);

    List<Inventory> viewInventory() throws SQLException;

    List<Product> viewProducts() throws SQLException;

    Product getProduct(int id);


}
