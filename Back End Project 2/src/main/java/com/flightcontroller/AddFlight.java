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
import com.flight.models.Flight;



/**
 * Servlet implementation class AddFareDetails
 */
@WebServlet("/AddFlight")

public class AddFlight extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		String airline = request.getParameter("airline");
		String[] days = request.getParameterValues("weekdays");
		String weekdays = String.join("_", days);
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		Flight flight = new Flight();
		int flightNumber = 0;
		Admin_Dao admin = new Admin_Dao();
		HttpSession session = request.getSession(false);
		if((airline != null && airline != "") && (weekdays != null && weekdays != "") &&
				(source != null && source != "") && (destination != null && destination != "")) {

			flight.setAirline(airline);
			flight.setWeekdays(weekdays);
			flight.setSource(source);
			flight.setDestination(destination);
			flightNumber = admin.addFlight(flight); 

			if(flightNumber != 0) {
				request.setAttribute("SUCCESS", "Flight successfully added");
				rd = getServletContext().getRequestDispatcher("/admindetails.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("Error", "Error Occured while adding flight");
				rd = getServletContext().getRequestDispatcher("/adminaddflight.jsp");
				rd.forward(request, response);
			}


		}else {
			request.setAttribute("Error2", "Error Occured while adding flight");
			rd = getServletContext().getRequestDispatcher("/adminaddflight.jsp");
			rd.forward(request, response);
		}


	}


	
}
