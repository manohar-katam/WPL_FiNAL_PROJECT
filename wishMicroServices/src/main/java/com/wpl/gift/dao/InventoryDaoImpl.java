package com.wpl.gift.dao;

import com.wpl.gift.model.Inventory;
import com.wpl.gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: Manohar
 */
@Repository
public class InventoryDaoImpl implements InventoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public boolean addInventory(Inventory inventory) {

        try{
        jdbcTemplate.update("INSERT INTO PRODUCT (name, category_id, price, color, rating, exist) VALUES (?, ?, ?,?,?, '1')", inventory.getName(), inventory.getCategory_id(), inventory.getPrice(), inventory.getColor(), inventory.getRating());
        }
        catch(Exception e)
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
    try{


        jdbcTemplate.update("UPDATE PRODUCT SET exist=0 WHERE id=?",new Object[]{id});
    }
    catch(Exception e)
    {
        return false;
    }

        return true;
    }

    @Override
    public List<Inventory> viewInventory() throws SQLException {

        List<Map<String,Object>>  result=  new ArrayList<Map<String, Object>>();
        try{
                result = jdbcTemplate.queryForList(
                "SELECT * FROM PRODUCT WHERE exist != '0'");
        }
        catch(Exception e)
        {
            return null;
        }


        List<Inventory> inventoryList = new ArrayList<Inventory>();
        for(int j=0; j<result.size();j++)
        {
            Inventory inventory = mapRow(result.get(j),j);
            inventoryList.add(inventory);
        }

        return inventoryList;
    }

    @Override
    public List<Product> viewProducts() throws SQLException {

        List<Map<String,Object>>  result=  new ArrayList<Map<String, Object>>();
        try{
                result = jdbcTemplate.queryForList(
                "SELECT p.id,p.name,cat.name as category,col.name as color,p.price,p.rating FROM PRODUCT p,CATEGORY cat,COLOR col WHERE p.category_id=cat.id AND p.color = col.id AND p.exist != '0'");
        }
        catch(Exception e)
        {
            return null;
        }

        List<Product> inventoryList = new ArrayList<Product>();
        for(int j=0; j<result.size();j++)
        {
            Product product = mapProductRow(result.get(j),j);
            inventoryList.add(product);
        }

        return inventoryList;
    }

    private Product mapProductRow(Map<String, Object> rs, int j) {
        Product product = new Product();
        product.setId((Integer)rs.get("id"));
        product.setName((String)rs.get("name"));
        product.setCategory((String)rs.get("category"));
        product.setPrice((Integer) rs.get("price"));
        product.setColor((String)rs.get("color"));
        product.setRating((Integer) rs.get("rating"));
        return product;
    }


    @Override
    public Product getProduct(int id) {
        Product product = new Product();
        try{
            product = (Product) jdbcTemplate.queryForObject(
                    "SELECT p.id,p.name,cat.name as category,col.name as color,p.price,p.rating FROM PRODUCT p,CATEGORY cat,COLOR col WHERE p.category_id=cat.id AND p.color = col.id AND p.id = ? and p.exist = ?", new Object[]{id,1},
                    new ProductMapper());

        }catch (EmptyResultDataAccessException e) {
            return null;
        }
        return product;

    }


    public Inventory mapRow(Map<String,Object> rs, int rowNum) throws SQLException {

                Inventory inventory = new Inventory();
                inventory.setId((Integer) rs.get("id"));
                inventory.setName((String)rs.get("name"));
                inventory.setPrice((Integer) rs.get("price"));

            return inventory;
        }

    private static final class ProductMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getInt("price"));
            product.setColor(rs.getString("color"));
            product.setRating(rs.getInt("rating"));
            return product;
        }
    }


}
