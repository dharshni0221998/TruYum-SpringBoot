package com.cognizant.truyum.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.truyum.dao.CartEmptyException;
import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.CartService;
import com.cognizant.truyum.service.MenuItemService;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	@Autowired
	private MenuItemService menuItemService;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@GetMapping("/add-to-cart")
	public String addToCart(ModelMap map, @RequestParam long id) {
		LOGGER.info("Start");
		cartService.addCartItem(1, id);
		map.addAttribute("addCartStatus", true);
		List<MenuItem> menuItemListCustomer = menuItemService.getMenuItemListCustomer();
		map.addAttribute("menuItemListCustomer", menuItemListCustomer);
		LOGGER.info("End");
		return "menu-item-list-customer";
	}

	@GetMapping("/show-cart")
	public String showCart(ModelMap map, @RequestParam long userId) {
		LOGGER.info("Start");
		try {
			Cart cart = cartService.getAllCartItems(userId);
			map.addAttribute("cart", cart);

		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			return "cart-empty";
		}
		LOGGER.info("End");
		return "cart";
	}

	@GetMapping("/remove-cart")
	public String removeCart(ModelMap map, @RequestParam long id, @RequestParam long userId) {
		LOGGER.info("Start");
		cartService.removeCartItem(userId, id);
		try {
			Cart cart = cartService.getAllCartItems(userId);
			map.addAttribute("cart", cart);
			map.addAttribute("removeCartItemStatus", true);
		} catch (CartEmptyException e) {
			// TODO Auto-generated catch block
			return "cart-empty";
			// e.printStackTrace();
		}
		LOGGER.info("End");
		return "cart";
	}

}
