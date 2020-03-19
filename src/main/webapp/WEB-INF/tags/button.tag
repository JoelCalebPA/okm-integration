<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.Button"%>
<%@attribute name="button" required="true"
	type="com.openkm.sdk4j.bean.form.Button"%>

<button type="button" name="${ button.name }" value="${ button.label }"
	class="btn btn-primary mb-2">${ button.label }</button>