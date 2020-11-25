package com.cognizant.truyum.util;
import java.sql.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component
@ImportResource("classpath:spring-config.xml")
public class ConnectionHandler {
	
	@Autowired
	private DataSource ds;
	
	public  Connection getConnection(){
		Connection con =null;
		/*String url="jdbc:mysql://localhost:3306/lch_marketplace";
		String user="cts877962";
		String password="cts877962";*/
		try {
			con=ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}

}
