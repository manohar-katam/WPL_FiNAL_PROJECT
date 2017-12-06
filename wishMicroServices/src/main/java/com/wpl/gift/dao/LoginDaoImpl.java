/**
 * 
 */
package com.wpl.gift.dao;
import java.security.spec.ECField;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.wpl.gift.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * @author Sushma
 *
 */
@Service("loginDao")
@Transactional
public class LoginDaoImpl implements LoginDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public User login(String userName, String password) {
	    User pUser = new User();
        try{
             pUser = (User) jdbcTemplate.queryForObject(
                    "SELECT * FROM USER WHERE username = ?", new Object[]{userName},
                    new UserMapper());
          if(pUser.getPassword().equals(password))
            {
                 return pUser;
            }
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
        return null;

	}

	@Override
	public boolean authenticate(User user) {
		try{
			User pUser = (User) jdbcTemplate.queryForObject(
					"SELECT * FROM USER WHERE username = ?", new Object[]{user.getUserName()},
					new UserMapper());
			if(pUser.getPassword().equals(user.getPassword()))
			{
				return true;
			}
		}catch (EmptyResultDataAccessException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean validateUser(User user) {

		try{
			jdbcTemplate.queryForObject(
					"SELECT * FROM USER WHERE username = ?", new Object[]{user.getUserName()},
					new UserMapper());
			return false;
		}catch (EmptyResultDataAccessException e) {
			return true;
		}
	}
	@Override
	public User register(User user) {

		try {
			jdbcTemplate.update("INSERT INTO USER (username,password,firstname,lastname,security_question,sec_answer) VALUES (?, ?, ?,?,?,?)", user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getSecurityQuestion(), user.getSecurityAnswer());
		}
		catch(Exception e)
		{
			return null;
		}
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
