<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.TextArea"%>
<%@attribute name="textarea" required="true"
	type="com.openkm.sdk4j.bean.form.TextArea"%>

<label for="${ textarea.name }">${ textarea.label }</label>
<c:choose>
	<c:when test="${ textarea.readonly }">
		<input type="text" multiple="multiple" name="${ textarea.name }"
			value="${ textarea.data }" readonly="readonly" />
	</c:when>
	<c:otherwise>
		<input type="text" multiple="multiple" name="${ textarea.name }"
			value="${ textarea.data }" />
	</c:otherwise>
</c:choose>
