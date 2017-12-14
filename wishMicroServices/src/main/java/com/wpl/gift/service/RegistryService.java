/**
 * 
 */
package com.wpl.gift.service;

import com.wpl.gift.model.Registry;
import com.wpl.gift.model.RegistryItem;

import java.sql.SQLException;
import java.util.List;

/**
 * Author: Manohar
 */

public interface RegistryService {
    Registry createRegistry(Registry registry);

    Registry saveRegistry(Registry registry);

    Registry saveUsers(Registry registry);

    List<RegistryItem> viewRegistry(int id) throws SQLException;

    List<RegistryItem> viewSharedRegistry(int id) throws SQLException;

    List<Registry> viewRegistryList(int id) throws SQLException;

    List<Registry> viewSharedRegistryList(int id) throws SQLException;

    boolean selfAssign(int id, int id2);

    boolean addRegistryItem(RegistryItem registryItem);

    boolean delRegItem(int id);
}
