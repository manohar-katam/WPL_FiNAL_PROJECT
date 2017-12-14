package com.wpl.gift.dao;


import com.wpl.gift.model.Registry;
import com.wpl.gift.model.RegistryItem;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
/**
 * Author: Manohar
 */
@Component
public interface RegistryDao {
    Registry createRegistry(Registry registry);

    Registry saveRegistry(Registry pRegistry);

    Registry saveUsers(Registry pRegistry);

    List<Registry> viewRegistryList(int id) throws SQLException;

    List<RegistryItem> viewRegistry(int id) throws SQLException;

    List<Registry> viewSharedRegistryList(int id) throws SQLException;

    List<RegistryItem> viewSharedRegistry(int id) throws SQLException;

    boolean selfAssign(int id, int id2);
}
