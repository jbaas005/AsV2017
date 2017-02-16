<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String uri = request.getRequestURI();
    String pageName = uri.substring(uri.lastIndexOf("/") + 1, uri.length() - 4);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><spring:message code='<%=pageName + ".webTitle"%>' /></title>
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/favicon.ico" />">
</head>
<body> 
<div class="container">
<div class="row">
    <div class="clearfix" style="height:100px;">
        <div style="height:90px;">
            <img src="<c:url value="/resources/img/stiho.png" />" alt="Stiho" style="float:left;" />
            <h1 style="color:#cc6805; padding-top:39px;"></h1>
             <div style="float:right;"><spring:message code="complaint.form.language" />: <a href="${pageContext.request.contextPath}/?lang=en">English</a> | <a href="${pageContext.request.contextPath}/?lang=nl">Nederlands</a></div>
        </div>
    </div> 
</div>
<div class="row" style="margin-top:15px;">    
    <nav class="navbar navbar-default">
        <div class="container-fluid pnull">
            <div class="collapse navbar-collapse pnull" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <c:if test="${sessionScope.id != null}">
                    <p class="navbar-text" style="color:#FA8E23">Welkom ${sessionScope.name}</p>
                    </c:if>
                    <li <% if (pageName.equals("index")) { %> class="active" <% } %>>
                        <a href="${pageContext.request.contextPath}/">
                            <spring:message code="nav.home" />
                        </a>
                    </li>
                    <c:if test="${sessionScope.id == null}">
                    <li <% if (pageName.equals("faq")) { %> class="active" <% } %>>
                        <a href="${pageContext.request.contextPath}/faq">
                            <spring:message code="nav.faq" />
                        </a>
                    </li>
                    <li <% if (pageName.equals("form")) { %> class="active" <% } %>>
                        <a href="${pageContext.request.contextPath}/complaint">
                            <spring:message code="nav.newComplaint" />
                        </a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right" style="margin-right:-16px;">
                    <li <% if (pageName.equals("login")) { %> class="active" <% } %>>
                        <a href="${pageContext.request.contextPath}/login">
                            <spring:message code="nav.logIn" />
                        </a>
                    </li>
                </ul> 
                </c:if>
                <c:if test="${sessionScope.id != null}">
                        <c:if test="${sessionScope.isManager == null}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="complaints" /><span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="${pageContext.request.contextPath}/complaint/overview/all"><spring:message code="complaints.all" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/complaint/overview/open"><spring:message code="complaints.open" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/complaint/overview/unhandled"><spring:message code="complaints.unhandled" /></a></li>
                                <li><a href="${pageContext.request.contextPath}/complaint/overview/handled"><spring:message code="complaints.handled" /></a></li>
                            </ul>
                        </li>
                        <li <% if (pageName.equals("customerList")) { %> class="active" <% } %>>
                            <a href="${pageContext.request.contextPath}/customerList">
                                <spring:message code="customers" />
                            </a>
                        </li>
                        </c:if>   
                        <c:if test="${sessionScope.isManager != null}">
                        <li <% if (pageName.equals("mindex")) { %> class="active" <% } %>>
                            <a href="${pageContext.request.contextPath}/manager"><spring:message code="complaints" /></a>
                        </li>
                        <li <% if (pageName.equals("employees")) { %> class="active" <% } %>>
                            <a href="${pageContext.request.contextPath}/manager/employees"><spring:message code="employees" /></a>
                        </li>
                        <li <% if (pageName.equals("statistics")) { %> class="active" <% } %>>
                            <a href="${pageContext.request.contextPath}/manager/statistics"><spring:message code="statistics" /></a>
                        </li>
                        <li <% if (pageName.equals("customerList")) { %> class="active" <% } %>>
                            <a href="${pageContext.request.contextPath}/customerList"><spring:message code="customers" /></a>
                        </li>
                        </c:if> 
                    </ul>    
                    <ul class="nav navbar-nav navbar-right" style="margin-right:-16px;">
                    <li>
                        <a href="${pageContext.request.contextPath}/logout">
                            <spring:message code="nav.logOut" />
                        </a>
                    </li>
                    </ul> 
                </c:if>   
            </div>        
        </div>
    </nav>
    <hr></hr>
</div> 