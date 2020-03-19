<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.Select"%>
<%@attribute name="select" required="true"
	type="com.openkm.sdk4j.bean.form.Select"%>

<div class="form-group row">
	<label for="${ select.name }" class="col-sm-2 col-form-label">${ select.label }</label>
	<div class="col-sm-10">
		<select name="${ select.name }" class="form-control">
			<c:forEach var="opt" items="${ select.options }">
				<option value="${ opt.value }">${ opt.label }</option>
			</c:forEach>
		</select>
	</div>
</div>