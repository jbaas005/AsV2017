<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="path" required="true" %>
<%@attribute name="id" required="true" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>

<spring:message code="${path}" var="label" />

<spring:bind path="${path}">
    <div class="form-group${status.error ? ' has-error' : '' }">
        <label class="col-md-4 control-label" for="${id}">${label}${required ? ' *' : ''}</label>  
        <div class="col-md-4">
            <form:input path="${path}" id="${id}" placeholder="${label}" cssClass="form-control" />
            <p class="help-block"><form:errors path="${path}" cssClass="error" /></p>
        </div>
    </div>
</spring:bind>