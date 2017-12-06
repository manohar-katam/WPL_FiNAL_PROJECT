package com.wpl.gift.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;


import com.wpl.gift.model.Inventory;
import com.wpl.gift.model.InventoryModel;
import com.wpl.gift.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Author Manohar
 */
@Controller
public class InventoryController {

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public String addInventory(ModelMap model){
        model.addAttribute("inventory",new Inventory());
        return "add-inventory";
    }

    @RequestMapping(value = "/viewInventory", method = RequestMethod.GET, produces = "application/json")
    public String viewInventory(ModelMap model){

        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewInventory);
            InventoryModel result = restTemplate.getForObject(builder.build().encode().toUri(),InventoryModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("inventoryList", result.getInventoryList() );
                return "view-inventory";
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

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET, produces = "application/json")
    public String deleteProduct(@RequestParam("id") int id){
        Response response;
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.deleteProduct);
            builder.queryParam("id", id);
            response = restTemplate.getForObject(builder.build().encode().toUri(),Response.class);

            if(response.getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {

                return "redirect:/viewInventory";
            }
            else
            {
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";

    }

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public String register(HttpServletRequest request,ModelMap model,@ModelAttribute("inventory") Inventory pInventory) throws ParseException {
        Response response;

        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForObject(Route.basePath+Route.addInventory,pInventory, Response.class);

            if(response.getResponseCode().equalsIgnoreCase(ConstantMessages.creationCode))
            {
                return "redirect:/viewInventory";
            }
            else
            {
                model.addAttribute("errorMessage", response.getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";
    }
}
