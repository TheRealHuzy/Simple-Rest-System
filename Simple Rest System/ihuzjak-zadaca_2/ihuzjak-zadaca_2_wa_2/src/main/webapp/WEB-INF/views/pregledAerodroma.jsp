<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Huzjak - zadaća 2 - pregled aerodroma</title>
</head>
<body>
	<h1>Pregled aerodroma</h1>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/pocetak">Početak</a><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi">Pregled svih aerodroma</a>
	<br>
	<table border="1">
		<tr>
			<th>ICAO</th>
			<th>NAME</th>
			<th>MUNICIPALITY</th>
			<th>CONTINENT</th>
		</tr>
		<c:forEach var="a" items="${requestScope.aerodromiAE}">
			<tr>
				<td>${a.ident}</td>
				<td>${a.name}</td>
				<td>${a.municipality}</td>
				<td>${a.continent}</td>
			</tr>
		</c:forEach>
	</table><br>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/${requestScope.icao}/dolasci">Dolasci</a>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/${requestScope.icao}/polasci">Polasci</a>
</body>
</html>