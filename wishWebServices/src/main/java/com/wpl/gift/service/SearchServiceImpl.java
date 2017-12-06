
package com.wpl.gift.service;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.InventoryModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wpl.gift.common.Route;
/**
 *  Author Sneha
 */
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {

	@Override
	public InventoryModel searchItems(String keywords) {
		InventoryModel inventoryModel = new InventoryModel();
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.searchCall);
		builder.queryParam("keywords", keywords);

		inventoryModel = restTemplate.getForObject(builder.build().encode().toUri(), InventoryModel.class);

		if(inventoryModel.getInventoryList().size() > 0)
		{
			System.out.print("Micro Service has returned correctly");
		}
		return inventoryModel;
	}

	@Override
	public InventoryModel filterItems(Filter filter) {
		InventoryModel inventoryModel = new InventoryModel();
		RestTemplate restTemplate = new RestTemplate();
		inventoryModel = restTemplate.postForObject(Route.basePath+Route.filterCall,filter, InventoryModel.class);

		return inventoryModel;
	}
}
