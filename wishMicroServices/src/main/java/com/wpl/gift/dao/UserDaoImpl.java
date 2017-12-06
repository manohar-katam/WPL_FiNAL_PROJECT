package com.wpl.gift.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.wpl.gift.model.User;

import org.springframework.stereotype.Repository;


import javax.sql.DataSource;

/**
 * Author Kartheek
 */
@Repository
public class UserDaoImpl implements UserDao{

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource (DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserInfo(int userId) {
        User pUser = new User();
try {
    pUser = (User) jdbcTemplate.queryForObject(
            "SELECT * FROM USER WHERE id = ?", new Object[]{userId},
            new UserMapper());
}
        catch(Exception e)
        {
            return null;
        }
        return pUser;
    }


    @Override
    public User updateUserInfo(User user ) {
        User pUser = new User();

       try{
        if( jdbcTemplate.update("UPDATE  USER SET firstname = ? , lastname = ?  WHERE id = ?", user.getFirstName(),user.getLastName(),user.getId()) ==1){
            pUser = (User) jdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE id = ?", new Object[]{user.getId()},
                    new UserMapper());
        }
    }
        catch(Exception e)
    {
        return null;
    }

        return pUser;
    }



    @Override
    public User getSecurityQuestions(User user ) {
        User pUser = new User();

        try{
            pUser = (User) jdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE username=?", new Object[]{user.getUserName()},
                    new UserMapper());

        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }

        return pUser;
    }


    @Override
    public User resetPassword(User user ) {
        User pUser = new User();

        try{
            pUser = (User) jdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE id=? AND   sec_answer = ?", new Object[]{user.getId()  ,user.getSecurityAnswer()},
                    new UserMapper());
            if( jdbcTemplate.update("UPDATE  USER SET password = ?  WHERE id = ?", user.getPassword(),user.getId()) ==0){
                return  null;
            }
        }


        catch (EmptyResultDataAccessException e) {
            return null;
        }

        return pUser;
    }



    @Override
    public List<User> getUsers(int id){

        List<Map<String,Object>>  result = new ArrayList<Map<String, Object>>();
        try{
            result =  jdbcTemplate.queryForList(
                "SELECT * FROM USER WHERE id NOT IN (?,1)",new Object[]{id});
        }
        catch(Exception e)
        {
            return null;
        }
        List<User> userList = new ArrayList<User>();
        for(int j=0; j<result.size();j++)
        {
            User user = null;
            try {
                user = mapRow(result.get(j),j);
            } catch (SQLException e) {
                return null;
            }
            userList.add(user);
        }

        return userList;
    }


    public User mapRow(Map<String,Object> rs, int rowNum) throws SQLException {

        User user = new User();
        user.setId((Integer) rs.get("id"));
        user.setFirstName((String)rs.get("firstname"));
        user.setLastName((String)rs.get("lastname"));
        return user;
    }

    private static final class UserMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setSecurityQuestion(rs.getString("security_question"));
            user.setSecurityAnswer(rs.getString("sec_answer"));
            return user;
        }
    }



}
