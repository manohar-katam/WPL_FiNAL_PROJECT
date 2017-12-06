
package com.wpl.gift.service;

import java.sql.SQLException;
import java.util.List;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.Product;
import org.springframework.stereotype.Component;

/**
 * Author Sneha
 */
@Component
public interface SearchService {

	List<Product> search(String keywords) throws SQLException;

    List<Product> filter(Filter filter);
}
