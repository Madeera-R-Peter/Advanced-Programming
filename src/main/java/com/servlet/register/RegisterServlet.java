package com.servlet.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

public class RegisterServlet extends HttpServlet{
	
	
	
	//query
	
	private static final String INSERT_QUERY = "INSERT INTO CUSTOMER(EMAIL,USERNAME,PASSWORD) VALUES(?,?,?)";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWrite
		PrintWriter pw = resp.getWriter();
		
		resp.setContentType("text/html");
		
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("psw");
		
//		System.out.println("Email: "+email);
//		System.out.println("Username: "+username);
//		System.out.println("Password: "+password);
		
		
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//create connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///thejobs","root","Peter@mysql@1998");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
		
			//set values
			ps.setString(1, email);
			ps.setString(2, username);
			ps.setString(3, password);
			
			//run query
			
			int count = ps.executeUpdate();
			if(count==0) {
				pw.println("Failed to Register");
			}else {
				pw.println("Registration Successful");

			}
			
		}catch(SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
		pw.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
