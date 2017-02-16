<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<% 
    Integer uid = (Integer)request.getSession().getAttribute("id"); 
    if (uid == null) {
        response.sendRedirect("login");
    }
%>
<t:header/>
<div id="complaints">
    <div class="row">
        <input type="text" class="search" placeholder="Search"/>
    </div>
    <div class="row">          
        <form:form method="GET" action="${pageContext.request.contextPath}/customerHistory">
            <table id="mtable" class="table tablesorter" style="border: 1px solid #DDD">
                <thead>
                    <tr>
                        <th></th>
                        <th><spring:message code="customerID" /></th>
                        <th><spring:message code="customer.companyName" /></th>
                        <th><spring:message code="mail" /></th>
                        <th><spring:message code="customer.companyAddress" /></th>
                        <th><spring:message code="customer.companyCity" /></th>
                        <th><spring:message code="customer.companyPostalCode" /></th>
                    </tr>
                </thead>
                <tbody class="list">
                    <c:forEach var="customers" items="${customer}">  
                        <tr>
                            <td ><INPUT TYPE="radio" NAME="customerId" VALUE="${customers.getCustomerId()}" CHECKED></td>                                        
                            <td class="customerId">${customers.getCustomerId()}</td>
                            <td class="companyName">${customers.getCompanyName()}</td>    
                            <td class="email">${customers.getEmail()}</td>   
                            <td class="companyAddress">${customers.getCompanyAddress()}</td>   
                            <td class="companyCity">${customers.getCompanyCity()}</td>   
                            <td class="companyPostalCode">${customers.getCompanyPostalCode()}</td>   
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <input type="submit" value="Show History"/>
        </form:form>
    </div>
</div>   
<t:footer/>