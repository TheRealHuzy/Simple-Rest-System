<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Huzjak - zadaća 2 - pregled pracenih aerodroma</title>
</head>
<body>
	<h1>Pregled pracenih aerodroma</h1>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/pocetak">Početak</a>
	<br>
	<table border="1">
		<tr>
			<th>ICAO</th>
		</tr>
		<c:forEach var="a" items="${requestScope.praceni}">
			<tr>
				<td>${a.ident}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test = "${requestScope.trenutniBroj > 1}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje?stranica=1">Prvi</a>
	</c:if>
	<c:if test = "${requestScope.trenutniBroj-1 > 1}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje?stranica=${requestScope.trenutniBroj-1}">${requestScope.trenutniBroj-1}</a>
	</c:if>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje?stranica=${requestScope.trenutniBroj}"> ::${requestScope.trenutniBroj}::</a>
	<c:if test = "${requestScope.trenutniBroj+1 < requestScope.zadnjiBroj}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje?stranica=${requestScope.trenutniBroj+1}">${requestScope.trenutniBroj+1}</a>
	</c:if>
	<c:if test = "${requestScope.trenutniBroj < requestScope.zadnjiBroj}">
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje?stranica=${requestScope.zadnjiBroj}">Zadnji</a> 
	</c:if>
</body>
</html>