<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Huzjak - zadaća 2 - pregled svih aerodroma</title>
</head>
<body>
	<h1>Pregled svih aerodroma</h1>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/pocetak">Početak</a>
	<br>
	<table border="1">
		<tr>
			<th>ICAO</th>
			<th>NAME</th>
			<th>MUNICIPALITY</th>
			<th>CONTINENT</th>
		</tr>
		<c:forEach var="a" items="${requestScope.aerodromi}">
			<tr>
				<td><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/${a.ident}">${a.ident}</a></td>
				<td>${a.name}</td>
				<td>${a.municipality}</td>
				<td>${a.continent}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test = "${requestScope.trenutniBroj > 1}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi?stranica=1">Prvi</a>
	</c:if>
	<c:if test = "${requestScope.trenutniBroj-1 > 1}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi?stranica=${requestScope.trenutniBroj-1}">${requestScope.trenutniBroj-1}</a>
	</c:if>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi?stranica=${requestScope.trenutniBroj}"> ::${requestScope.trenutniBroj}::</a>
	<c:if test = "${requestScope.trenutniBroj+1 < requestScope.zadnjiBroj}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi?stranica=${requestScope.trenutniBroj+1}">${requestScope.trenutniBroj+1}</a>
	</c:if>
	<c:if test = "${requestScope.trenutniBroj < requestScope.zadnjiBroj}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi?stranica=${requestScope.zadnjiBroj}">Zadnji</a> 
	</c:if>
</body>
</html>