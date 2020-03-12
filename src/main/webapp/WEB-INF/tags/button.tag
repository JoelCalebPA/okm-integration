<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.Button"%>
<%@attribute name="button" required="true"
	type="com.openkm.sdk4j.bean.form.Button"%>

<style>
.enlace {
	display: inline;
	border: 0;
	padding: 0;
	margin: 0;
	text-decoration: underline;
	background: none;
	color: #000088;
	font-family: arial, sans-serif;
	font-size: 1em;
	line-height: 1em;
}
.enlace:hover {
	text-decoration: none;
	color: #0000cc;
	cursor: pointer;
}
</style>

<button type="button" name="${ button.name }" value="${ button.label }" >${ button.label }</button>