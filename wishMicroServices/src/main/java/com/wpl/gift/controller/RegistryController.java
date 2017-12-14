package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;
import com.wpl.gift.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Author Manohar, Sneha
 */
@Controller
public class RegistryController {

    @Autowired
    RegistryService registryService;

    @RequestMapping(value = Route.createRegistry, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel createRegistry(@RequestBody Registry registry) {

        Response response;

        RegistryModel registryModel = new RegistryModel();
        registryModel.setRegistry(registryService.createRegistry(registry));

        if (registryModel.getRegistry()==null)
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        else
        {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.creationCode);
        }

        registryModel.setResponse(response);
        return registryModel;
    }

    @RequestMapping(value = Route.saveRegistry, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel saveRegistry(@RequestBody Registry registry) {

        Response response;
        RegistryModel registryModel = new RegistryModel();
        registryModel.setRegistry(registryService.saveRegistry(registry));

        if (registryModel.getRegistry() ==null)
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        else
        {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }

        registryModel.setResponse(response);
        return registryModel;
    }


    @RequestMapping(value = Route.saveUsers, method = RequestMethod.POST,produces="application/json")
    public @ResponseBody RegistryModel saveUsers(@RequestBody Registry registry) {

        Response response;
        RegistryModel registryModel = new RegistryModel();
        registryModel.setRegistry(registryService.saveUsers(registry));

        if (registryModel.getRegistry() ==null)
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        else
        {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }

        registryModel.setResponse(response);
        return registryModel;
    }

    @RequestMapping(value = Route.viewRegistryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryModel registryModel(int id) throws SQLException {
        RegistryModel registryModel = new RegistryModel();

        Response response;
        Registry registry = new Registry();

        List<RegistryItem> list = registryService.viewRegistry(id);
        registry.setRegistryItems(list);

        if(list!= null) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        registryModel.setRegistry(registry);
        registryModel.setResponse(response);
        return registryModel ;
    }

    @RequestMapping(value = Route.viewSharedRegistryPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryModel sharedRegistryModel(int id) throws SQLException {
        RegistryModel registryModel = new RegistryModel();
        Response response = new Response();
        Registry registry = new Registry();
        List<RegistryItem> list = registryService.viewSharedRegistry(id);
        registry.setRegistryItems(list);

        if(list!= null) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        registryModel.setRegistry(registry);
        registryModel.setResponse(response);
        return registryModel ;
    }

    @RequestMapping(value = Route.viewRegistryListPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryListModel registryListModel(int id) throws SQLException {
        RegistryListModel registryModel = new RegistryListModel();

        Response response = new Response();
        List<Registry> list = registryService.viewRegistryList(id);

        if(list!= null) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        registryModel.setRegistryList(list);
        registryModel.setResponse(response);
        return registryModel ;
    }

    @RequestMapping(value = Route.viewSharedRegistryListPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody RegistryListModel sharedRegistryListModel(int id) throws SQLException {
        RegistryListModel registryModel = new RegistryListModel();
        Response response = new Response();
        List<Registry> list = registryService.viewSharedRegistryList(id);

        if(list!= null) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        registryModel.setRegistryList(list);
        registryModel.setResponse(response);
        return registryModel ;
    }

    @RequestMapping(value = Route.viewSelfAssignPage, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Response response(@RequestParam(value="id") int id, @RequestParam(value="id2") int id2 ) {
        Response response;
        if(registryService.selfAssign(id, id2)) {
            response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }
        return response;
    }


    @RequestMapping(value = Route.addRegistryItem, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response addItem( @RequestBody RegistryItem registryItem) throws SQLException {

        Response response = new Response();
        boolean result = registryService.addRegistryItem(registryItem);

        if(result) {
            response = new Response(ConstantMessages.foundResult,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }

        return response ;
    }

    @RequestMapping(value = Route.delRegistryItem, method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Response delItem(@RequestParam("id") int id) throws SQLException {

        Response response = new Response();
        boolean result = registryService.delRegItem(id);

        if(result) {
            response = new Response(ConstantMessages.foundResult,ConstantMessages.successCode);
        }
        else
        {
            response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
        }

        return response ;
    }

}
