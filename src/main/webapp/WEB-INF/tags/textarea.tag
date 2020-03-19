<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@tag import="com.openkm.sdk4j.bean.form.TextArea"%>
<%@attribute name="textarea" required="true"
	type="com.openkm.sdk4j.bean.form.TextArea"%>

<div class="form-group row">
	<label for="${ textarea.name }" class="col-sm-2 col-form-label">${ textarea.label }</label>
	<div class="col-sm-10">
		<c:choose>
			<c:when test="${ textarea.readonly }">
				<textarea class="form-control" readonly="readonly"
					name="${ textarea.name }">${ textarea.data }</textarea>
			</c:when>
			<c:otherwise>
				<textarea class="form-control" name="${ textarea.name }">${ textarea.data }</textarea>
			</c:otherwise>
		</c:choose>
	</div>
</div>