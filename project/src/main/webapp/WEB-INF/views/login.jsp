<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<% 
    Integer uid = (Integer)request.getSession().getAttribute("id"); 
    if (uid != null) {
        response.sendRedirect("#");
    }
%>
<t:header/>
<form:form cssClass="form-horizontal" commandName="login" action="${pageContext.request.contextPath}/login" method="post" enctype="multipart/form-data">
<div class="row">
    ${errors != null ? "<div class='alert alert-danger'>".concat(errors).concat("</div>") : ""}
    <div class="col-md-4 col-md-offset-4">                          
        <div class="login-panel panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"><spring:message code="login.title" /></h3>
            </div>
            <div class="panel-body">
                <fieldset>
                    <div class="form-group" style="margin:20px">
                        <form:input cssClass="form-control" style="margin-bottom:20px" placeholder="E-mail" path="email" type="email" autofocus="true" />
                        <form:input class="form-control" style="margin-bottom:20px" placeholder="Password" path="password" type="password" value="" />
                        <button id="submitButton" type="submit" class="btn btn-warning btn-lg btn-block"><spring:message code="login.btn" /></button>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>
</div>
</form:form>
<t:footer/>