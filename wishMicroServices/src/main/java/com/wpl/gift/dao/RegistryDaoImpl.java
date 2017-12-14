package com.wpl.gift.dao;

import com.wpl.gift.model.Registry;
import com.wpl.gift.model.RegistryItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Author: Manohar
 */
@Repository
public class RegistryDaoImpl implements RegistryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Registry createRegistry(Registry registry) {

        Registry pRegistry;
        try {
            jdbcTemplate.update("INSERT IGNORE INTO REGISTRY (reg_name,user_id,shared_flag) VALUES (?, ?, ?)", registry.getName(), registry.getUserId(), registry.getShared().equalsIgnoreCase("public") ? 1 : 0);

            pRegistry = (Registry) jdbcTemplate.queryForObject(
                    "SELECT * FROM Registry WHERE reg_name = ? and user_id = ?", new Object[]{registry.getName(), registry.getUserId()},
                    new RegistryMapper());
        }
        catch(Exception e)
        {
            return null;
        }
        return pRegistry;

    }

    @Override
    public Registry saveRegistry(Registry pRegistry) {

        List<RegistryItem> itemList = pRegistry.getRegistryItems();

        for(int k=0; k<itemList.size();k++) {
            RegistryItem temp = itemList.get(k);
           try{
            jdbcTemplate.update("INSERT INTO REGISTRY_ITEM (reg_id,product_id,price,assignee_id) VALUES (?, ?,?,1)", pRegistry.getId(),temp.getProductId(), temp.getPrice());
           }
           catch(Exception e)
           {
               return null;
           }

        }

        return pRegistry;
    }

    @Override
    public Registry saveUsers(Registry pRegistry) {

        List<Integer> userList = pRegistry.getUserIds();

        for(int k=0; k<userList.size();k++) {

           try{
               jdbcTemplate.update("INSERT INTO SHARED (reg_id,user_id) VALUES (?, ?)", pRegistry.getId(),userList.get(k));
           }
           catch(Exception e)
           {
               return null;
           }

        }

        return pRegistry;
    }


    @Override
    public List<Registry> viewRegistryList(int id) throws SQLException {

        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        try{
                result = jdbcTemplate.queryForList(
                "SELECT r.id,r.reg_name,u.firstname,u.lastname FROM REGISTRY r,USER u WHERE r.user_id=u.id AND r.user_id=? ",new Object[]{id});
        }
        catch(Exception e)
        {
            return null;
        }

        List<Registry> registryList = new ArrayList<Registry>();

        for(int k=0;k<result.size();k++)
        {
            Registry temp = mapRow(result.get(k));
            registryList.add(temp);
        }


        return registryList;
    }


    @Override
    public List<RegistryItem> viewRegistry(int id) throws SQLException {

        List<Map<String,Object>> itemResult = new ArrayList<Map<String, Object>>();
        try{
                itemResult = jdbcTemplate.queryForList(
                "SELECT r.id,p.name,cat.name as category,co.name as color,p.rating,r.price,u.firstname FROM REGISTRY_ITEM r,PRODUCT p,CATEGORY cat, COLOR co,USER u WHERE r.product_id=p.id AND p.category_id=cat.id AND p.color=co.id AND r.assignee_id = u.id AND r.reg_id=? ",new Object[]{id});
        }
        catch(Exception e)
        {
            return null;
        }

        List<RegistryItem> itemList = new ArrayList<RegistryItem>();
        for(int m=0;m<itemResult.size();m++)
        {
            RegistryItem temp = mapItemRow(itemResult.get(m));
            itemList.add(temp);
        }

        return itemList;
    }

    @Override
    public List<Registry> viewSharedRegistryList(int id) throws SQLException {

        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        try{
                result = jdbcTemplate.queryForList(
                "SELECT r.id,r.reg_name,u.firstname,u.lastname FROM REGISTRY r,USER u WHERE r.user_id=u.id AND r.user_id != ? AND SHARED_FLAG=1 UNION SELECT reg.id, reg.reg_name,usr.firstname,usr.lastname FROM REGISTRY reg,SHARED s,USER usr WHERE reg.id=s.reg_id AND reg.user_id=usr.id AND reg.user_id != ? AND reg.shared_flag=0 AND s.user_id=?",new Object[]{id,id,id});
        }
        catch(Exception e)
        {
            return null;
        }

        List<Registry> registryList = new ArrayList<Registry>();

        for(int k=0;k<result.size();k++)
        {
            Registry temp = mapRow(result.get(k));
            registryList.add(temp);
        }

        return registryList;
    }


    @Override
    public List<RegistryItem> viewSharedRegistry(int id) throws SQLException {

        List<Map<String,Object>> itemResult = new ArrayList<Map<String, Object>>();

        try{
            itemResult = jdbcTemplate.queryForList(
                    "SELECT r.id,p.name,cat.name as category,co.name as color,p.rating,r.price,u.firstname FROM REGISTRY_ITEM r,PRODUCT p,CATEGORY cat, COLOR co,USER u WHERE r.product_id=p.id AND p.category_id=cat.id AND p.color=co.id AND r.assignee_id = u.id AND r.reg_id=? ",new Object[]{id});
        }
        catch(Exception e)
        {
            return null;
        }


        List<RegistryItem> itemList = new ArrayList<RegistryItem>();
        for(int m=0;m<itemResult.size();m++)
        {
            RegistryItem temp = mapItemRow(itemResult.get(m));
            itemList.add(temp);
        }
        return itemList;
    }

    private Map<Registry,List<RegistryItem>> getRegistryItems(List<Map<String,Object>> result) throws SQLException {

        List<Registry> registryList = new ArrayList<Registry>();

        for(int k=0;k<result.size();k++)
        {
            Registry temp = mapRow(result.get(k));
            registryList.add(temp);
        }

        Map<Registry,List<RegistryItem>> resList = new HashMap<Registry, List<RegistryItem>>();

        for(int j=0;j<registryList.size();j++)
        {

            Registry registry = registryList.get(j);
            List<Map<String,Object>> itemResult = new ArrayList<Map<String, Object>>();
             try{
                    itemResult = jdbcTemplate.queryForList(
                    "SELECT r.id,p.name,cat.name as category,co.name as color,p.rating,r.price,u.firstname FROM REGISTRY_ITEM r,PRODUCT p,CATEGORY cat, COLOR co,USER u WHERE r.product_id=p.id AND p.category_id=cat.id AND p.color=co.id AND r.assignee_id = u.id AND r.reg_id=? ",new Object[]{registry.getId()});
             }
             catch(Exception e)
             {
                 return null;
             }

            List<RegistryItem> itemList = new ArrayList<RegistryItem>();
            for(int m=0;m<itemResult.size();m++)
            {
                RegistryItem temp = mapItemRow(itemResult.get(m));
                itemList.add(temp);
            }

            resList.put(registry,itemList);
        }

        return resList;
    }
    @Override
    public boolean selfAssign(int id, int id2) {

        try{
        jdbcTemplate.update("UPDATE REGISTRY_ITEM SET assignee_id=? WHERE id=?",new Object[]{id, id2});
        }
        catch(Exception e)
        {
            return false;
        }

        return true;
    }


    private Registry mapRow(Map<String,Object> rs) throws SQLException {

        Registry registry = new Registry();
        registry.setId((Integer) rs.get("id"));
        registry.setName((String)rs.get("reg_name"));
        registry.setUserName((String)rs.get("firstname") +" " + (String)rs.get("lastname"));
        return registry;
    }


    private RegistryItem mapItemRow(Map<String,Object> rs) throws SQLException {

        RegistryItem registryItem = new RegistryItem();
        registryItem.setId((Integer) rs.get("id"));
        registryItem.setName((String)rs.get("name"));
        registryItem.setCategory((String)rs.get("category"));
        registryItem.setColor((String)rs.get("color"));
        registryItem.setAssigned((String)rs.get("firstname"));
        registryItem.setPrice((Integer)rs.get("price"));
        registryItem.setRating((Integer)rs.get("rating"));

        return registryItem;
    }

    private static final class RegistryMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Registry registry = new Registry();
            registry.setId(rs.getInt("id"));
            registry.setName(rs.getString("reg_name"));
            registry.setUserId(rs.getInt("user_id"));
            registry.setShared(rs.getString("shared_flag"));
            return registry;
        }
    }

}
