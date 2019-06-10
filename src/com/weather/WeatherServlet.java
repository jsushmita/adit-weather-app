package com.weather;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeatherServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cityNameString = (String) request.getParameter("cityName");
		String weatherInfo = getWeather(cityNameString);
		request.setAttribute("weather", weatherInfo);
		request.getRequestDispatcher("").forward(request, response);
	}

	private String getWeather(String cityNameString) {
		String result = "";
		
		return result;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// make post and get method the same implementation in the first edition
		doGet(request, response);
	}

}
