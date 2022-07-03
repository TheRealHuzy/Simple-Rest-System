<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Huzjak - zadaća 2</title>
</head>
<body>
	<h1>Brisanje problema aerodroma</h1>
	<a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/pocetak">Početak</a></li>
	<form method="post">
		<select name="slcPosaljiUpit">
			<c:forEach var="a" items="${requestScope.brisanje}">
				<option>${a.ident}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Submit">
	</form>
</body>
</html>