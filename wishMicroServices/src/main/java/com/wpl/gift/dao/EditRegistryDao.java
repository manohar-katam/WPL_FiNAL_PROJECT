package com.wpl.gift.dao;

import com.wpl.gift.model.RegistryItem;
import org.springframework.stereotype.Component;

@Component
public interface EditRegistryDao {

    boolean delRegistryItem(int id);

    boolean addRegistryItem(RegistryItem item);
}
