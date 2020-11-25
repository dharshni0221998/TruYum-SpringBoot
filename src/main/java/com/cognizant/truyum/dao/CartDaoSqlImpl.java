package com.cognizant.truyum.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.ConnectionHandler;

@Component("cartDao")
public class CartDaoSqlImpl implements CartDao {
	
	@Autowired
	private ConnectionHandler con;
	public static PreparedStatement ps = null;
	public static int i = 1;
	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

	public void addCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		String str = "insert into cart values(?,?,?)";
		try {
			ps = con.getConnection().prepareStatement(str);
			ps.setInt(1, i++);
			ps.setInt(2, (int) userId);
			ps.setInt(3, (int) menuItemId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Inserted Sucessfully");
			}
			ps.clearParameters();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Cart getAllCartItems(long userId) throws CartEmptyException {
		// TODO Auto-generated method stub
		Cart cart = new Cart(new ArrayList<MenuItem>(), 0);
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();
		String str = "select m.menu_id,menu_name,menu_price,menu_active,menu_date_of_launch,menu_category,menu_free_delivery from cart c,menu_Item m where c.menu_id=m.menu_id and c.user_id=?";
		try {
			ps = con.getConnection().prepareStatement(str);
			ps.setInt(1, (int) userId);
			ResultSet rs = ps.executeQuery();
			ps.clearParameters();
			boolean flag = false;
			while (rs.next()) {
				flag = true;
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				boolean active = rs.getString(4).equalsIgnoreCase("yes") ? true : false;
				Date dateOfLaunch = null;
				try {
					dateOfLaunch = format1.parse(format1.format(rs.getDate(5)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String category = rs.getString(6);
				boolean freeDel = rs.getString(7).equalsIgnoreCase("yes") ? true : false;

				menuItemList.add(new MenuItem(id, name, price, active, dateOfLaunch, category, freeDel));
				cart.setMenuItemList(menuItemList);

			}
			if (!flag) {
				throw new CartEmptyException();
			}
			str = "select sum(menu_price) from menu_item m,cart c where m.menu_id=c.menu_id and c.user_id=? group by user_id";
			ps = con.getConnection().prepareStatement(str);
			ps.setInt(1, (int) userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				cart.setTotal(rs.getFloat(1));
			}
			System.out.println(cart.getTotal());
			return cart;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void removeCartItem(long userId, long menuItemId) {
		// TODO Auto-generated method stub
		String str = "delete from cart where user_id=? and menu_id=?";
		try {
			ps = con.getConnection().prepareStatement(str);
			ps.setInt(1, (int) userId);
			ps.setInt(2, (int) menuItemId);
			if (ps.executeUpdate() > 0) {
				System.out.println("Deleted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}