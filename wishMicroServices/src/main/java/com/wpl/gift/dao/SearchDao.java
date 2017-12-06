/**
 * 
 */
package com.wpl.gift.dao;

import java.sql.SQLException;
import java.util.List;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.Product;
import org.springframework.stereotype.Component;


@Component
public interface SearchDao {
	
	List<Product> searchItems(String keywords) throws SQLException;


    List<Product> filterItems(Filter filter);
}
