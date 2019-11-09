<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Le Bon Point</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Emilys+Candy&display=swap" rel="stylesheet">
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} |
				<button class="btn btn-lg btn-primary btn-block"
					onclick="document.forms['logoutForm'].submit()">Logout</button>
			</h2>

		</c:if>
		<%@ include file="menu.jsp"%>
		<h2>Annonce Information</h2>
		<table>
			<tr>
				<td>Title :</td>
				<td>${title}</td>
			</tr>
			<tr>
				<td>Date :</td>
				<td>${date}</td>
			</tr>
			<tr>
				<td>Price :</td>
				<td>${price}</td>
			</tr>
			<tr>
				<td>Category :</td>
				<td>${category}</td>
			</tr>
			<tr>
				<td>Content :</td>
				<td>${content}</td>
			</tr>
			<tr>
				<td>Auteur :</td>
				<td>${username}</td>
			</tr>
		</table>
		<form action="/annonce/delete/${ id }" method="get">
			<button type="submit">Delete</button>
		</form>

		<a href="/">Retour</a>
		<%@ include file="footer.jsp"%>
	</div>
</body>