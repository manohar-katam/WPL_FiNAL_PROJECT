/**
 * 
 */
package com.wpl.gift.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.wpl.gift.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.service.SearchService;

/**
 * Author Sneha
 */
@Controller
public class SearchController {
	

	@Autowired SearchService searchService;

	@RequestMapping(value = Route.search, method = RequestMethod.GET,produces="application/json")
	public @ResponseBody InventoryModel searchItem(@RequestParam(value = "keywords") String keywords) throws SQLException {
		List<Product> items = new ArrayList<Product>();
		Response response = new Response();
		InventoryModel inventoryModel = new InventoryModel();
			if (keywords == null)
			{
				response.setResponseCode(ConstantMessages.mandatoryParameterMissingCode);
				response.setResponseMessage(ConstantMessages.noSearchResult);
				//Response to the search api
				inventoryModel.setResponse(response);
				inventoryModel.setInventoryList(items);
			}
			else
			{
					items = searchService.search(keywords);
					//Response to the search result
					response.setResponseCode(ConstantMessages.successCode);
					response.setResponseMessage(ConstantMessages.foundResult);
			
			}
			response.setResponseCode(ConstantMessages.successCode);
			response.setResponseMessage(ConstantMessages.foundResult);
			inventoryModel.setResponse(response);
			inventoryModel.setInventoryList(items);
		return inventoryModel;
	}

	@RequestMapping(value = Route.filter, method = RequestMethod.POST,produces="application/json")
	public @ResponseBody InventoryModel filterItem(@RequestBody Filter filter) throws SQLException {
		List<Product> items = new ArrayList<Product>();
		Response response = new Response();
		InventoryModel inventoryModel = new InventoryModel();
		if (filter == null)
		{
			response.setResponseCode(ConstantMessages.mandatoryParameterMissingCode);
			response.setResponseMessage(ConstantMessages.noSearchResult);
			//Response to the search api
			inventoryModel.setResponse(response);
			inventoryModel.setInventoryList(items);
		}
		else
		{
			items = searchService.filter(filter);
			//Response to the search result
			response.setResponseCode(ConstantMessages.successCode);
			response.setResponseMessage(ConstantMessages.foundResult);

		}
		response.setResponseCode(ConstantMessages.successCode);
		response.setResponseMessage(ConstantMessages.foundResult);
		inventoryModel.setResponse(response);
		inventoryModel.setInventoryList(items);
		return inventoryModel;
	}
}
