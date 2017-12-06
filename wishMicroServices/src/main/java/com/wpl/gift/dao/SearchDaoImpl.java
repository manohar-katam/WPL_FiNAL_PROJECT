/**
 * 
 */
package com.wpl.gift.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SearchDaoImpl implements SearchDao{
   private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public List<Product> searchItems(String q) throws SQLException {
        String[] keywords = q.split(" ");
        String statement =" ";
        List<Map<String, Object>> result = null;
        String query = " ";
        statement="";
        for (int i = 0; i < keywords.length; i++) {
            statement = statement + "SELECT p.id,p.name,cat.name as category,col.name as color,p.price,p.rating FROM PRODUCT p,CATEGORY cat,COLOR col WHERE p.category_id=cat.id AND p.color = col.id AND p.name like '" + keywords[i] + "%' UNION";
        }
        query= statement.substring(0, statement.lastIndexOf(" "));

        try{
        result = jdbcTemplate.queryForList(query);
        }
        catch(Exception e)
        {
            return null;
        }

        List<Product> list = new ArrayList<Product>();
        for(int k=0;k<result.size();k++)
        {
            Product temp = mapProductRow(result.get(k),k);
            list.add(temp);
        }


        return list;
    }

    @Override
    public List<Product> filterItems(Filter filter) {

        List<Map<String, Object>> result = null;

        try{
            result = jdbcTemplate.queryForList("SELECT p.id,p.name,cat.name as category,col.name as color,p.price,p.rating " +
                    "FROM PRODUCT p,CATEGORY cat,COLOR col WHERE p.category_id=cat.id AND p.color = col.id " +
                    "AND p.category_id = ? AND p.color = ? AND p.price < ? AND p.rating = ? ", new Object[]{filter.getCategory(),filter.getColor(),filter.getPrice(),filter.getRating()});
        }
        catch(Exception e)
        {
            return null;
        }

        List<Product> list = new ArrayList<Product>();
        for(int k=0;k<result.size();k++)
        {
            Product temp = mapProductRow(result.get(k),k);
            list.add(temp);
        }


        return list;
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



}