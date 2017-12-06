/**
 * 
 */
package com.wpl.gift.service;

import java.sql.SQLException;
import java.util.List;

import com.wpl.gift.dao.SearchDao;
import com.wpl.gift.model.Filter;
import com.wpl.gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Author Sneha
 */
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {


	@Autowired
	SearchDao searchDao;

	 @Override
	 public List<Product> search(String keywords) throws SQLException {
		 List<Product> items = searchDao.searchItems(keywords);
		 return items;
	}

	@Override
	public List<Product> filter(Filter filter) {
		List<Product> items = searchDao.filterItems(filter);
		return items;
	}

}
