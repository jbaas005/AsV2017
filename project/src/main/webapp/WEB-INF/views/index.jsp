<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:header/>

<div class="row">
    <div class="jumbotron" style="background: url(<c:url value="/resources/img/hero.jpg" />);color:#fff;text-shadow:0 1px 0 #000;">
        <h1>
        <spring:message code='index.welcome' arguments='</h1><p>,<br/>,</p><p>'/>
        </p>
        <p>
            <a class="btn btn-lg btn-danger" href="${pageContext.request.contextPath}/complaint/" role="button">
                <spring:message code="index.fileComplaint"/> »
            </a>
            of 
            <a class="btn btn-lg btn-warning" href="${pageContext.request.contextPath}/login" role="button">
                <spring:message code="index.logIn"/> »
            </a>
        </p>
    </div>
</div>

<t:footer/> 