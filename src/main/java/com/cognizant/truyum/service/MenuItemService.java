package com.cognizant.truyum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.MenuItemDao;
import com.cognizant.truyum.model.MenuItem;

@Service
/**
 * 
 * @author 877962
 *
 */
public class MenuItemService {

	
	/**
	 * MenuItemDaoCollectionImpl object will be injected
	 */
	@Autowired
	private MenuItemDao menuItemDao;

	public MenuItemDao getMenuItemDao() {
		return menuItemDao;
	}

	
	public void setMenuItemDao(MenuItemDao menuItemDao) {
		this.menuItemDao = menuItemDao;
	}

	/**
	 * 
	 * @return
	 */
	public List<MenuItem> getMenuItemListAdmin() {

		return menuItemDao.getMenuItemListAdmin();

	}

	/**
	 * 
	 * @return
	 */
	public List<MenuItem> getMenuItemListCustomer() {
		return menuItemDao.getMenuItemListCustomer();

	}

	/**
	 * 
	 * @param menuItemId
	 * @return
	 */
	public MenuItem getMenuItem(long menuItemId) {
		return menuItemDao.getMenuItem(menuItemId);

	}

	/**
	 * 
	 * @param menuItem
	 */
	public void editMenuItem(MenuItem menuItem) {

		menuItemDao.modifyMenuItem(menuItem);
	}

}
