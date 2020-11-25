package com.cognizant.truyum.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.MenuItemService;


@Controller
public class MenuItemController {
	@Autowired
	private MenuItemService menuItemService;
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping("/show-menu-list-admin")
	public String showMenuItemListAdmin(ModelMap model) {
		LOGGER.info("Start");
		List<MenuItem> menuItemListAdmin = menuItemService.getMenuItemListAdmin();
		model.addAttribute("menuItemListAdmin", menuItemListAdmin);
		LOGGER.info("End");
		return "menu-item-list-admin";
	}
	
	@GetMapping("/show-menu-list-customer")
	public String showMenuItemListCustomer(ModelMap model) {
		LOGGER.info("Start");
		List<MenuItem> menuItemListCustomer = menuItemService.getMenuItemListCustomer();
		model.addAttribute("menuItemListCustomer", menuItemListCustomer);
		LOGGER.info("End");
		return "menu-item-list-customer";
	}
	
	@GetMapping("/show-edit-menu-item")
	public String showEditMenuItem(ModelMap model,@RequestParam long id,@ModelAttribute("menuItem") MenuItem menuItem,BindingResult bindingResult) {
		LOGGER.info("Start");
	   MenuItem  item = menuItemService.getMenuItem(id);
	   model.put("item", item);
		LOGGER.info("End");
		return "edit-menu-item";
	}
	
	@PostMapping("/edit-menu-item")
	public String editMenuItem(@Valid @ModelAttribute("menuItem") MenuItem menuItem,BindingResult bindingResult) {
		LOGGER.info("Start");
		if(bindingResult.hasErrors()) {
			return "edit-menu-item";
		}
		menuItemService.editMenuItem(menuItem);
		LOGGER.info("End");
		return"edit-menu-item-status";
	}
	
	

}
