package com.wpl.gift.service;

import com.wpl.gift.dao.InventoryDao;
import com.wpl.gift.model.Inventory;
import com.wpl.gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

/**
 * Author Manohar
 */
@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryDao inventoryDao;

    @Override
    public boolean addInventory(Inventory inventory) {
        return  inventoryDao.addInventory(inventory);
    }

    @Override
    public List<Product> viewInventory() throws SQLException {

        return inventoryDao.viewProducts();

    }

    @Override
    public boolean deleteProduct(@RequestParam(value="id") int id) {

        return inventoryDao.deleteProduct(id);
    }


    @Override
    public Product getProduct(int id) {
        return inventoryDao.getProduct(id);
    }

}
