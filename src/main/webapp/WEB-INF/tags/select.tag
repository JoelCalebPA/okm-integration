<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.Select"%>
<%@attribute name="select" required="true"
	type="com.openkm.sdk4j.bean.form.Select"%>

<label for="${ select.name }">${ select.label }</label>
<select name="${ select.name }">
	<c:forEach var="opt" items="${ select.options }">
		<option value="${ opt.value }">${ opt.label }</option>
	</c:forEach>
</select>