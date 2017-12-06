
package com.wpl.gift.controller;

import com.wpl.gift.model.Filter;
import com.wpl.gift.model.InventoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wpl.gift.common.Route;
import com.wpl.gift.service.SearchService;

/**
 *  Author Sneha
 */
@Controller
public class SearchController {
	
	@Autowired SearchService searchService;

	@RequestMapping(value = Route.search, method = RequestMethod.GET,produces="application/json")
	public @ResponseBody InventoryModel search(@RequestParam(value = "keywords") String keywords) {
		InventoryModel inventoryModel = new InventoryModel();
		inventoryModel = searchService.searchItems(keywords);
		return inventoryModel;
	}

	@RequestMapping(value = Route.filter, method = RequestMethod.POST,produces="application/json")
	public @ResponseBody InventoryModel search(@RequestBody Filter filter) {
		InventoryModel inventoryModel = new InventoryModel();
		inventoryModel = searchService.filterItems(filter);
		return inventoryModel;
	}


}
