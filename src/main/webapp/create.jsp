<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Le Bon Point</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
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

		<form:form method="POST" action="/addAnnonce" modelAttribute="annonce">
			<table>
				<tr>
					<td><form:hidden path="username" value="${ username }" /></td>
				</tr>
				<tr>
					<td><form:label path="title">Title</form:label></td>
					<td><form:errors path="title"></form:errors></td>
					<td><form:input path="title" /></td>
				</tr>
				<tr>
					<td><form:label path="price">Price</form:label></td>
					<td><form:errors path="price"></form:errors></td>
					<td><form:input path="price" /></td>
				</tr>
				<tr>
					<td><form:label path="category">Category</form:label></td>
					<td><form:errors path="category"></form:errors></td>
					<td><form:input path="category" /></td>
				</tr>
				<tr>
					<td><form:label path="content">Description</form:label></td>
					<td><form:errors path="content"></form:errors></td>
					<td><form:input path="content" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>