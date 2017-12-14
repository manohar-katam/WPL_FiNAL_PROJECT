package com.wpl.gift.service;

import com.wpl.gift.common.Route;
import com.wpl.gift.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
/**
 *  Author Sneha, Manohar
 */
@Service("registryService")
@Transactional
public class RegistryServiceImpl implements RegistryService {
    @Override
    public RegistryModel createRegistry(Registry registry) {
        RegistryModel registryModel = new RegistryModel();
        RestTemplate restTemplate = new RestTemplate();
        registryModel = restTemplate.postForObject(Route.basePath+Route.createRegistryCall,registry, RegistryModel.class);
        return registryModel;
    }

    @Override
    public RegistryModel saveRegistry(Registry registry) {
        RegistryModel registryModel = new RegistryModel();
        RestTemplate restTemplate = new RestTemplate();
        registryModel = restTemplate.postForObject(Route.basePath+Route.saveRegistryCall,registry, RegistryModel.class);
        return registryModel;
    }

    @Override
    public RegistryModel saveUsers(Registry registry) {

        RegistryModel registryModel = new RegistryModel();
        RestTemplate restTemplate = new RestTemplate();
        registryModel = restTemplate.postForObject(Route.basePath+Route.saveUsersCall,registry, RegistryModel.class);
        return registryModel;
    }
    @Override
    public RegistryModel viewRegistry(int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewRegistry);
        builder.queryParam("id",id);
        RegistryModel result = restTemplate.getForObject(builder.build().encode().toUri(), RegistryModel.class);

        return result;

    }

    @Override
    public RegistryModel viewSharedRegistry(int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewSharedRegistry);
        builder.queryParam("id",id);
        RegistryModel result = restTemplate.getForObject(builder.build().encode().toUri(), RegistryModel.class);

        return result;
    }

    @Override
    public Response selfAssign(int id, int id2) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.selfAssign);
        builder.queryParam("id", id);
        builder.queryParam("id2", id2);
        Response response = restTemplate.getForObject(builder.build().encode().toUri(), Response.class);

        return response;
    }

    @Override
    public RegistryListModel viewRegistryList(int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewRegistryList);
        builder.queryParam("id",id);
        RegistryListModel result = restTemplate.getForObject(builder.build().encode().toUri(), RegistryListModel.class);

        return result;
    }

    @Override
    public RegistryListModel viewSharedRegistryList(int id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.viewSharedRegistryList);
        builder.queryParam("id",id);
        RegistryListModel result = restTemplate.getForObject(builder.build().encode().toUri(), RegistryListModel.class);

        return result;
    }


    @Override
    public Response addRegistryItem(RegistryItem registryItem) {
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject(Route.basePath+Route.addRegistryItemCall,registryItem, Response.class);

        return result;
    }

    @Override
    public Response delRegItem(int id) {
        RestTemplate restTemplate = new RestTemplate();

        Response result = restTemplate.postForObject(Route.basePath+Route.delRegistryItemCall,id, Response.class);


        return result;
    }
}
