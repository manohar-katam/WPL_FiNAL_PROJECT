package com.wpl.gift.controller;

import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;
import com.wpl.gift.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 *  Author Manohar
 */
@Controller
public class RegistryController {

    @Autowired
    RegistryService registryService;

    @RequestMapping(value = Route.createRegistry, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel createRegistry(@RequestBody Registry registry) {

        return registryService.createRegistry(registry);
    }

    @RequestMapping(value = Route.saveRegistry, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel saveRegistry(@RequestBody Registry registry) {

        return registryService.saveRegistry(registry);
    }

    @RequestMapping(value = Route.saveUsers, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel saveUsers(@RequestBody Registry registry) {

        return registryService.saveUsers(registry);
    }

    @RequestMapping(value = Route.viewRegistryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    RegistryModel viewRegistry(int id){

        return registryService.viewRegistry(id);
    }

    @RequestMapping(value = Route.viewSharedRegistryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    RegistryModel viewSharedRegistry(int id){

        return registryService.viewSharedRegistry(id);
    }

    @RequestMapping(value = Route.viewRegistryListPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryListModel viewRegistryList(int id){

        return registryService.viewRegistryList(id);
    }

    @RequestMapping(value = Route.viewSharedRegistryListPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryListModel viewSharedRegistryList(int id){

        return registryService.viewSharedRegistryList(id);
    }


    @RequestMapping(value = Route.viewSelfAssignPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Response response(@RequestParam(value="id") int id, @RequestParam(value="id2") int id2){
        return registryService.selfAssign(id, id2);
    }

    @RequestMapping(value = Route.addRegistryItem, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response addItem(@RequestBody RegistryItem registryItem) throws SQLException {


        return registryService.addRegistryItem(registryItem);
    }

    @RequestMapping(value = Route.delRegistryItem, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response delItem(@RequestParam("id") int id) throws SQLException {

        return registryService.delRegItem(id);
    }

}
