package com.wpl.gift.service;


import com.wpl.gift.model.*;
import org.springframework.stereotype.Component;
/**
 *  Author Sneha, Manohar
 */
@Component
public interface RegistryService {
    RegistryModel createRegistry(Registry registry);

    RegistryModel saveRegistry(Registry registry);

    RegistryModel saveUsers(Registry registry);

    RegistryModel viewRegistry(int id);

    RegistryModel viewSharedRegistry(int id);

    Response selfAssign(int id, int id2);

    RegistryListModel viewRegistryList(int id);

    RegistryListModel viewSharedRegistryList(int id);

    Response addRegistryItem(RegistryItem registryItem);

    Response delRegItem(int id);
}
