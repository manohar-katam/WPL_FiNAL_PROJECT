package com.wpl.gift.dao;

import com.wpl.gift.model.RegistryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class EditRegistryDaoImpl implements EditRegistryDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean delRegistryItem(int id) {
        try {
            jdbcTemplate.update("DELETE FROM REGISTRY_ITEM WHERE id=" + id);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean addRegistryItem(RegistryItem item) {
        try {
            jdbcTemplate.update("INSERT INTO REGISTRY_ITEM (reg_id,product_id,price,assignee_id) VALUES (?, ?,?,1)",item.getRegistryId(),item.getProductId(), item.getPrice());
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
