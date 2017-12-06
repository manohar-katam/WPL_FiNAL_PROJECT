
package com.wpl.gift.service;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.InventoryModel;
import org.springframework.stereotype.Component;
/**
 *  Author Sneha
 */
@Component
public interface SearchService {

    InventoryModel searchItems(String keywords);

    InventoryModel filterItems(Filter filter);
}
