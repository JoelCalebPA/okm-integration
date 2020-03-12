<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="wf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../include/header.jsp" />
</head>
<body>
	<jsp:include page="../../include/menu.jsp" />
	<div class="container">
		<div class="jumbotron">
			<form action="">
				<c:forEach var="field" items="${ fields }">
					<wf:workflow-field workflowField="${ field }"></wf:workflow-field>
				</c:forEach>
				<br>
			</form>
		</div>
	</div>
</body>
</html>