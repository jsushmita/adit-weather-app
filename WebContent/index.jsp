<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Awesome Weather</title>
</head>
<body>
	<h1>This is an awesome weather.</h1>
	<form action="${pageContext.request.contextPath}/WeatherServlet" method="POST">
		<input type="text" name="cityName"/>
		<input type="submit">
	</form>
</body>
</html>