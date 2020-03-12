<%@tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.FormElement"%>
<%@tag import="com.openkm.sdk4j.bean.form.Select"%>
<%@tag import="com.openkm.sdk4j.bean.form.Button"%>
<%@tag import="com.openkm.sdk4j.bean.form.TextArea"%>
<%@tag import="com.openkm.sdk4j.bean.form.Input"%>
<%@attribute name="workflowField" required="true"
	type="com.openkm.sdk4j.bean.form.FormElement"%>
<%@tag import="java.lang.String"%>
<%@attribute name="data" required="false" type="java.lang.String"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags"%>


<c:choose>
	<c:when test="${workflowField.objClass.contains('TextArea')}">
		<%
			TextArea textarea = (TextArea) workflowField;
		%>
		<f:textarea textarea="<%=textarea%>"></f:textarea>
	</c:when>

	<c:when test="${workflowField.objClass.contains('Select')}">
		<%
			Select select = (Select) workflowField;
		%>
		<f:select select="<%=select%>"></f:select>
	</c:when>

	<c:when test="${workflowField.objClass.contains('Button')}">
		<%
			Button button = (Button) workflowField;
		%>
		<f:button button="<%=button%>"></f:button>
	</c:when>

	<c:otherwise>
            No comment sir...
         </c:otherwise>
</c:choose>

