/**
 * 
 */
package com.wpl.gift.service;

import com.wpl.gift.dao.EditRegistryDao;
import com.wpl.gift.dao.RegistryDao;
import com.wpl.gift.model.Registry;
import com.wpl.gift.model.RegistryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Author: Manohar
 */
@Service("registryService")
@Transactional
public class RegistryServiceImpl implements RegistryService {

	@Autowired
	RegistryDao registryDao;

	@Autowired
	EditRegistryDao editRegistryDao;

	@Override
	public Registry createRegistry(Registry pRegistry) {

		Registry registry = registryDao.createRegistry(pRegistry);

		return registry;
	}

	@Override
	public Registry saveRegistry(Registry pRegistry) {

        return registryDao.saveRegistry(pRegistry);

	}

    @Override
    public Registry saveUsers(Registry pRegistry) {
        return registryDao.saveUsers(pRegistry);
    }

	@Override
	public List<RegistryItem> viewRegistry(int id) throws SQLException {

		return registryDao.viewRegistry(id);

	}

	@Override
	public List<RegistryItem> viewSharedRegistry(int id) throws SQLException {
		return registryDao.viewSharedRegistry(id);
	}

	@Override
	public List<Registry> viewRegistryList(int id) throws SQLException {

		return registryDao.viewRegistryList(id);

	}

	@Override
	public List<Registry> viewSharedRegistryList(int id) throws SQLException {
		return registryDao.viewSharedRegistryList(id);
	}

	@Override
	public boolean selfAssign(int id, int id2) {
		return registryDao.selfAssign(id, id2);
	}

	@Override
	public boolean addRegistryItem(RegistryItem registryItem)
	{
		return editRegistryDao.addRegistryItem(registryItem);
	}

	@Override
	public boolean delRegItem(int id)
	{
		return editRegistryDao.delRegistryItem(id);
	}

}
