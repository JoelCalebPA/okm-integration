<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div class="container">
		<div class="jumbotron">
			<h2>Tasks</h2>
			<c:forEach var="task" items="${ tasks }">
				<li class="vertical-gap"><a
					href="<c:url value="/features/workflow/task?tiId=${ task.id }" />">${ task.name }</a></li>
			</c:forEach>
		</div>
	</div>
</body>
</html>
