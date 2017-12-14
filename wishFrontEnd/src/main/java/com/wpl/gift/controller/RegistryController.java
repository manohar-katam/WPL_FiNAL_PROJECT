package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;

import com.wpl.gift.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Manohar
 */

@Controller
@RequestMapping(value="registry")
public class RegistryController {

    @RequestMapping(value="/createRegistry",method= RequestMethod.GET,produces = "application/json")
    public ModelAndView createRegistry(HttpServletRequest request, ModelMap model) {

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        Registry registry= new Registry();
        registry.setUserId(userId);
        session.setAttribute("registry",registry);
        return new ModelAndView("createRegistry", "registry", new Registry());
    }

    @RequestMapping(value="/createRegistry",method= RequestMethod.POST,produces = "application/json")
    public String createReg(HttpServletRequest request, ModelMap model, @ModelAttribute("registry") Registry registry) {

        HttpSession session = request.getSession();

        registry.setUserId((Integer) session.getAttribute("id"));

        RestTemplate restTemplate = new RestTemplate();

        RegistryModel registryModel  = restTemplate.postForObject(Route.basePath + Route.createRegistry,registry,RegistryModel.class);

        Response response = registryModel.getResponse();
        session.setAttribute("registry", registryModel.getRegistry());

        if(registry.getShared().equals("Public"))
        {
            return "redirect:/registry/createRegistry/products";
        }
        else
        {
            return "redirect:/registry/createRegistry/users";
        }
    }


    @RequestMapping(value="/createRegistry/products",method= RequestMethod.GET,produces = "application/json")
    public String getProductsForRegistry(HttpServletRequest request, ModelMap model) {

        Response response = new Response();
        Filter filter = new Filter();
        model.addAttribute("filter",filter);

        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewInventory);
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

    @RequestMapping(value="/createRegistry/addProduct",method= RequestMethod.GET,produces = "application/json")
    public ModelAndView addProduct(HttpServletRequest request, ModelMap model,@RequestParam("productId") int productId) {

        Product product = getProduct(productId);

        RegistryItem registryItem = new RegistryItem();

        HttpSession session = request.getSession();
        List<RegistryItem> registryCart = (List<RegistryItem>)session.getAttribute("registryCart");

        if(registryCart == null)
        {
            registryCart = new ArrayList<RegistryItem>();

        }

        if (product != null) {
            registryItem.toRegistryItem(product);
            registryCart.add(registryItem);
            session.setAttribute("registryCart", registryCart);
        }
        ModelAndView mav = new ModelAndView("registryCart","regCart",registryCart);

        return mav;
    }
    @RequestMapping(value="/createRegistry/registryCart",method= RequestMethod.GET,produces = "application/json")
    public ModelAndView goToCart(HttpServletRequest request, ModelMap model,@RequestParam("productId") int productId) {


        HttpSession session = request.getSession();
        List<RegistryItem> registryCart = (List<RegistryItem>)session.getAttribute("registryCart");

        if(registryCart == null)
        {
            registryCart = new ArrayList<RegistryItem>();

        }

        ModelAndView mav = new ModelAndView("registryCart","regCart",registryCart);

        return mav;
    }

    @RequestMapping(value="/createRegistry/removeProduct",method= RequestMethod.GET,produces = "application/json")
    public ModelAndView removeProduct(HttpServletRequest request, ModelMap model,@RequestParam("id") int id) {

        HttpSession session = request.getSession();
        List<RegistryItem> list = (List<RegistryItem>) session.getAttribute("registryCart");
        if (list != null) {
            RegistryItem temp = new RegistryItem();
            for (RegistryItem c : list) {
                if (c.getId() == (id)) {
                    temp = c;
                    break;
                }
            }
                list.remove(temp);
            session.setAttribute("registryCart", list);
        }

        ModelAndView mav = new ModelAndView("registryCart","regCart",list);

        return mav;
    }

    @RequestMapping(value="/createRegistry/saveRegistry",method= RequestMethod.POST,produces = "application/json")
    public String submitRegistry(HttpServletRequest request, ModelMap model) {

        HttpSession session = request.getSession();

        Registry registry =(Registry) session.getAttribute("registry");

        List<RegistryItem> list = (List<RegistryItem>) session.getAttribute("registryCart");

        registry.setRegistryItems(list);

        RestTemplate restTemplate = new RestTemplate();

        RegistryModel registryModel = restTemplate.postForObject(Route.basePath+Route.saveRegistry,registry,RegistryModel.class);

        session.removeAttribute("registry");
        session.removeAttribute("registryCart");
        if(registryModel.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
        {
            return "redirect:/viewRegistryList";
        }
        else
        {
            model.addAttribute("errorMessage",registryModel.getResponse().getResponseMessage());
            return "error-404";
        }
    }


    private Product getProduct(int productId) {

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getProduct);
        builder.queryParam("id",productId);
        ProductModel result = restTemplate.getForObject(builder.build().encode().toUri(),ProductModel.class);

        return result.getProduct();
    }

    @RequestMapping(value="/createRegistry/users",method= RequestMethod.GET,produces = "application/json")
    public String getUsersForRegistry(HttpServletRequest request,ModelMap model) {

        Response response = new Response();

        HttpSession session = request.getSession();
        int userId =(Integer)session.getAttribute("id");

        Registry registry =(Registry) session.getAttribute("registry");
        model.addAttribute("registry",registry);

        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getUsers);
            builder.queryParam("userId",userId);
            UserListModel result = restTemplate.getForObject(builder.build().encode().toUri(),UserListModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                 model.addAttribute("userList", result.getUsers() );
                return "userList";
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

    @RequestMapping(value="/createRegistry/saveUsers",method= RequestMethod.POST,produces = "application/json")
    public String saveUsers(HttpServletRequest request, ModelMap model,@ModelAttribute("registry") Registry registry) {

        HttpSession session = request.getSession();

        Registry pRegistry =(Registry) session.getAttribute("registry");

        pRegistry.setUserIds(registry.getUserIds());
        RestTemplate restTemplate = new RestTemplate();

        RegistryModel registryModel = restTemplate.postForObject(Route.basePath+Route.saveUsers,pRegistry,RegistryModel.class);

        session.setAttribute("registry",registryModel.getRegistry());

        if(registryModel.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
        {
            return "redirect:/registry/createRegistry/products";
        }
        else
        {
            model.addAttribute("errorMessage",registryModel.getResponse().getResponseMessage());
            return "error-404";
        }
    }



}
