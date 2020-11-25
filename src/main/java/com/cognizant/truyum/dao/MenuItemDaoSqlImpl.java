package com.cognizant.truyum.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.cognizant.truyum.controller.MenuItemController;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.ConnectionHandler;

@Component("menuItemDao")
public  class MenuItemDaoSqlImpl implements MenuItemDao{
	
	@Autowired
	private ConnectionHandler con;
	public static PreparedStatement ps=null;
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemDaoSqlImpl.class);
	public SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd"); 
	public SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy"); 
	
	public List<MenuItem> getMenuItemListAdmin(){
		LOGGER.info("Start");
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		String str = "select * from menu_item";
		try {
			ps=con.getConnection().prepareStatement(str);
			ResultSet rs =ps.executeQuery();
			ps.clearParameters();
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				float price=rs.getFloat(3);
				boolean active = rs.getString(4).equalsIgnoreCase("yes")?true:false;
				Date dateOfLaunch=null;
				try {
				 dateOfLaunch =  format1.parse(format1.format(rs.getDate(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String category =rs.getString(6);
				boolean freeDel = rs.getString(7).equalsIgnoreCase("yes")?true:false;
				menuItemList.add(new MenuItem(id,name,price,active,dateOfLaunch,category,freeDel));
				
			}
			return menuItemList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return null;
		
		
	}
	public MenuItem getMenuItem(long menuItemId){
		LOGGER.info("Start");
		MenuItem menuItem =null;
		String str = "select * from menu_item where menu_id=?";
		try {
			ps=con.getConnection().prepareStatement(str);
			ps.setInt(1, (int) menuItemId);
			ResultSet rs =ps.executeQuery();
			ps.clearParameters();
			if(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				float price=rs.getFloat(3);
				boolean active = rs.getString(4).equalsIgnoreCase("yes")?true:false;
				Date dateOfLaunch=null;
				try {
				 dateOfLaunch =  format1.parse(format1.format(rs.getDate(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String category =rs.getString(6);
				boolean freeDel = rs.getString(7).equalsIgnoreCase("yes")?true:false;
				menuItem=new MenuItem(id,name,price,active,dateOfLaunch,category,freeDel);
				return menuItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return null;
		
	}
	public List<MenuItem> getMenuItemListCustomer() {
		LOGGER.info("Start");
		// TODO Auto-generated method stub
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		String str = "select * from menu_item where menu_active=? and menu_date_of_launch between menu_date_of_launch and now()";
		try {
			ps=con.getConnection().prepareStatement(str);
			ps.setString(1, "yes");
			ResultSet rs =ps.executeQuery();
			ps.clearParameters();
			while(rs.next()){
				int id=rs.getInt(1);
				String name=rs.getString(2);
				float price=rs.getFloat(3);
				boolean active = rs.getString(4).equalsIgnoreCase("yes")?true:false;
				Date dateOfLaunch=null;
				try {
				 dateOfLaunch =  format1.parse(format1.format(rs.getDate(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String category =rs.getString(6);
				boolean freeDel = rs.getString(7).equalsIgnoreCase("yes")?true:false;
				menuItemList.add(new MenuItem(id,name,price,active,dateOfLaunch,category,freeDel));
				
			}
			return menuItemList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
		return null;
	}
	public void modifyMenuItem(MenuItem menuItem) {
		LOGGER.info("Start");
		// TODO Auto-generated method stub
		String str = "update menu_item set menu_name=?,menu_price=?,menu_active=?,menu_date_of_launch=?,menu_category=?,menu_free_delivery=? where menu_id=?";
		
		try {
			ps=con.getConnection().prepareStatement(str);
			ps.setString(1,menuItem.getName());
			ps.setFloat(2, menuItem.getPrice());
			ps.setString(3,menuItem.isActive()?"yes":"no");
			java.sql.Date dateOfLauch = new java.sql.Date(menuItem.getDateOfLaunch().getTime());
			ps.setDate(4,dateOfLauch);
			ps.setString(5, menuItem.getCategory());
			ps.setString(6, menuItem.isFreeDelivery()?"yes":"no");
			ps.setInt(7, (int) menuItem.getId());
			if(ps.executeUpdate()>0){
				System.out.println("Updated Sucessfully");
			}
			ps.clearParameters();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.info("End");
	}

}