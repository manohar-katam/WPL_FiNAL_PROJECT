package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.model.Filter;
import com.wpl.gift.model.InventoryModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Author Sneha
 */
@Controller
public class SearchController {

    @RequestMapping(value="search",method = RequestMethod.GET,produces = "application/json")
    public String search(@RequestParam("keywords")String keywords, ModelMap model)
    {
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.search);

            builder.queryParam("keywords",keywords);
            InventoryModel result = restTemplate.getForObject(builder.build().encode().toUri(),InventoryModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("inventoryList", result.getInventoryList() );
                return "products";
            }
            else
            {
                model.addAttribute("errorMessage", result.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";

    }

    @RequestMapping(value="filter",method = RequestMethod.POST,produces = "application/json")
    public String filter(@ModelAttribute("filter") Filter filter,ModelMap model)
    {
        try {

            RestTemplate restTemplate = new RestTemplate();

            InventoryModel result = restTemplate.postForObject(Route.basePath+Route.filter,filter,InventoryModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("inventoryList", result.getInventoryList() );
                return "products";
            }
            else
            {
                model.addAttribute("errorMessage", result.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";

    }



}
