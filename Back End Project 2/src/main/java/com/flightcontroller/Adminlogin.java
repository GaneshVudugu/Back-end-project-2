package com.flightcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flight.dao.Admin_Dao;


/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/Adminlogin")

public class Adminlogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("emailaddress");
		String password = request.getParameter("password");
		
		RequestDispatcher rd;
		
		Admin_Dao admin = new Admin_Dao();
		
		if((email != null && email.trim() != "") && 
				(password != null && password.trim() != "")) {
			
			
			int adminId = admin.adminLogin(email, password);
			
			if(adminId != 0) {
				
				HttpSession session = request.getSession();
				session.setAttribute("adminId", adminId);
				rd = getServletContext().getRequestDispatcher("/admindetails.jsp");
	            rd.forward(request, response);	
			}else {
				
				request.setAttribute("loginerr", "Incorrect email or Password");
				rd = getServletContext().getRequestDispatcher("/adminlogin.jsp");
	            rd.forward(request, response);
			}
			
		}else {
			
			request.setAttribute("loginerr1", "Error Occurred while Loging in");
			rd = getServletContext().getRequestDispatcher("/adminlogin.jsp");
            rd.forward(request, response);
		}
		
		
	}

}
