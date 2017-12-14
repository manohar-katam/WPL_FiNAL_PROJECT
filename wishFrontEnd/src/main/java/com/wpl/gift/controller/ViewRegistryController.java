package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.model.RegistryListModel;
import com.wpl.gift.model.RegistryModel;
import com.wpl.gift.model.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author Manohar, Sneha
 */
@Controller
public class ViewRegistryController {


    @RequestMapping(value = "/viewRegistry", method = RequestMethod.GET, produces = "application/json")
    public String viewRegistry(HttpServletRequest request, ModelMap model,@RequestParam("regId") int regId){

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewRegistry);
            builder.queryParam("id",regId);
            RegistryModel result = restTemplate.getForObject(builder.build().encode().toUri(),RegistryModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("registry", result.getRegistry().getRegistryItems());
                return "view-registry";
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

    @RequestMapping(value = "/viewSharedRegistry", method = RequestMethod.GET, produces = "application/json")
    public String viewSharedRegistry(HttpServletRequest request,ModelMap model,@RequestParam("regId") int regId){
        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewSharedRegistry);
            builder.queryParam("id",regId);
            RegistryModel result = restTemplate.getForObject(builder.build().encode().toUri(),RegistryModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("registry", result.getRegistry().getRegistryItems());
                return "view-shared-registry";
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
    @RequestMapping(value = "/viewRegistryList", method = RequestMethod.GET, produces = "application/json")
    public String viewRegistryList(HttpServletRequest request, ModelMap model){

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewRegistryList);
            builder.queryParam("id",userId);
            RegistryListModel result = restTemplate.getForObject(builder.build().encode().toUri(),RegistryListModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("registryList", result.getRegistryList());
                return "view-registry-list";
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

    @RequestMapping(value = "/viewSharedRegistryList", method = RequestMethod.GET, produces = "application/json")
    public String viewSharedRegistryList(HttpServletRequest request,ModelMap model){
        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewSharedRegistryList);
            builder.queryParam("id",userId);
            RegistryListModel result = restTemplate.getForObject(builder.build().encode().toUri(),RegistryListModel.class);

            if(result.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                model.addAttribute("registryList", result.getRegistryList());
                return "view-shared-registry-list";
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

    @RequestMapping(value = "/selfAssign", method = RequestMethod.GET, produces = "application/json")
    public String selfAssign(HttpServletRequest request,@RequestParam("id") int id){

        HttpSession session = request.getSession();
        int userId = (Integer)session.getAttribute("id");
        Response response;
        try {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.selfAssign);
            builder.queryParam("id", userId);
            builder.queryParam("id2", id);
            ;
            response = restTemplate.getForObject(builder.build().encode().toUri(),Response.class);

            if(response.getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
                return "redirect:/viewSharedRegistryList?id="+userId;
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

}
