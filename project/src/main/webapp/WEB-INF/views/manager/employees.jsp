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
<div id="complaints">
    <div class="row">
        <input type="text" class="search" placeholder="Search"/>
        <a class ="btn btn-warning" href="${pageContext.request.contextPath}/manager/add"><spring:message code="manager.addEmployee" /></a>
    </div>
    <div class="row">
            <table id="mtable" class="table tablesorter" style="border: 1px solid #DDD">
                <thead>
                    <tr>
                        <th><spring:message code="employee" /> <spring:message code="id" /></th>
                        <th><spring:message code="employee" /> <spring:message code="mail" /></th>
                        <th><spring:message code="employee" /> <spring:message code="fullName" /></th>
                        <th><spring:message code="employee" /> <spring:message code="phone" /></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody class="list">
                    <c:forEach var="employees" items="${employee}">  
                        <tr class="nth-child">
                            <td class="employeeId">${employees.getEmployeeId()}</td>
                            <td class="employeeMail">${employees.getEmployeeMail()}</td>   
                            <td class="employeeName">${employees.getEmployeeName()}</td>   
                            <td class="employeePhone">${employees.getEmployeePhone()}</td>   
                            <td><a href="${pageContext.request.contextPath}/manager/employees/delete/${employees.getEmployeeId()}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a></td> 
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </div>
<t:footer/>
