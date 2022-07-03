<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Huzjak - zadaća 2</title>
</head>
<body>
	<ul>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi">Pregled svih aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/preuzimanje">Pregled praćenih aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/LDZA/dolasci">Pregled dolazaka na aerodrom</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/LDZA/polasci">Pregled polazaka s aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/problemi">Pregled problema aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/problemi/LDZA">Pregled problema jednog aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/LDZA">Pregled jednog aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/aerodromi/unosAerodroma">Unos aerodroma</a></li>
		<li><a href="${pageContext.servletContext.contextPath}/mvc/problemi/brisanjeProblemaAerodroma">Briši probleme</a></li>
	</ul>
</body>
</html>