<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<% 
    Integer uid = (Integer)request.getSession().getAttribute("id"); 
    Integer ism = (Integer)request.getSession().getAttribute("isManager"); 
    if (uid == null || ism == null) {
        response.sendRedirect("login");
    }
%>
<t:header/>
    <div class="row">
        <div id="complaints">
            <form:form method="POST" commandName="employee" action="${pageContext.request.contextPath}/manager/add" enctype="multipart/form-data">
                <div class="form-group"><div class="col-sm-10">
                        <label><spring:message code="fullName" /></label>
                        <input class="form-control" path="employeeName" name='employeeName'> 
                        <label><spring:message code="phone" /></label>
                        <input class="form-control" path="employeePhone" name='employeePhone'> 
                        <label><spring:message code="mail" /></label>
                        <input class="form-control" path="employeeMail" name='employeeMail'> 
                        <label><spring:message code="passWord" /></label>
                        <input class="form-control" path="passWord" name='passWord'> 
                        <input class="form-control" type="hidden" path='manager' name='manager' value='<c:out value="${id}" />'>
                    </div></div>
                <div class="col-sm-10">
                    <input type="submit" value="Add Employee"/>
                </div>
            </form:form>
        </div>
    </div>
<t:footer/>