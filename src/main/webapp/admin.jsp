<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Create an account</title>
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
		<div>
			<h2>Nombre annonces: ${ nb }</h2>

			<form method = "get" action="/admin/moderate">
				<c:forEach items="${ result }" var="result">
					<li><a href="annonce/${ result.annonceId }"><c:out
								value="${ result.title }" /></a></li>
					<input type="checkbox" name="moderated" value="${ result.annonceId }"></input>
				</c:forEach>
				<button type="submit">Validate</button>
			</form>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	
</body>
</html>