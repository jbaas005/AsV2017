<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<% 
    Integer uid = (Integer)request.getSession().getAttribute("id"); 
    Integer ism = (Integer)request.getSession().getAttribute("isManager"); 
    if (uid == null || ism != null) {
        response.sendRedirect("login");
    }
%>
<t:header/>
<div id="complaints">
    <div class="row">
        <input type="text" class="search" placeholder="Search"/>
    </div>
    <div class="row">
        <table id="mtable" class="table tablesorter" style="border: 1px solid #DDD">
            <thead>
                <tr>
                    <th><spring:message code="complaint" /> <spring:message code="id" /></th>
                    <th><spring:message code="status" /></th>
                    <th><spring:message code="category" /></th>
                    <th><spring:message code="contactName" /></th>
                    <th><spring:message code="contactLastName" /></th>
                    <th><spring:message code="phone" /></th>
                    <th><spring:message code="mail" /></th>
                    <th><spring:message code="priority" /></th>
                    <th><spring:message code="createdAt" /></th>
                    <th><spring:message code="responseDeadline" /></th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="list">
                <c:forEach var="complaints" items="${complaint}">  
                    <tr class="nth-child">
                        <td class="complaintId">${complaints.getComplaintId()}</td>
                        <td class="status">${complaints.getFullyQualifiedStatus()}</td>   
                        <td class="category">${complaints.getCategory()}</td>   
                        <td class="firstName">${complaints.getContactName()}</td>   
                        <td class="lastName">${complaints.getContactLastName()}</td>   
                        <td class="phone">${complaints.getContactPhone()}</td>   
                        <td class="email">${complaints.getContactEmail()}</td>   
                        <td class="priority">${complaints.getStringPriority()}</td>
                        <td class="createdAt">${complaints.getFormattedCreatedAt()}</td>
                        <td class="deadLine">${complaints.getDeadline()}</td>
                        <td><a href="${pageContext.request.contextPath}/complaintDetails/${complaints.getComplaintId()}"><spring:message code="showBtn" /></a></td> 
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
    </div>
</div>
<t:footer/>