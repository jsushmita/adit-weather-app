<%@page import="org.json.JSONTokener"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<%
		String weatherInfo = (String) request.getAttribute("weather");
		if(weatherInfo==null){
			return;
		}
		JSONObject weatherJsonObject = new JSONObject(weatherInfo);
	%>
	<%=weatherJsonObject.getString("city") %>
	<%=weatherJsonObject.getString("temp") %>
</body>
</html>