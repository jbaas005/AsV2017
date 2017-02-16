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
<div class="row">
    <c:forEach var="complaints" items="${complaint}">
    <form:form method="POST" commandName="complaintDetails" action="${pageContext.request.contextPath}/complaint/details/${complaints.getComplaintId()}">
        <div class="form-group">
            <label><spring:message code="id" /></label>
            <input class="form-control" id="disabledInput" path="complaintId" placeholder="${complaints.getComplaintId()}" disabled>
            <label for="disabledSelect"><spring:message code="fullName" /></label>
            <input class="form-control" id="disabledInput" type="text" placeholder="${complaints.getContactName()} ${complaints.getContactLastName()} " disabled>
            <label for="disabledSelect"><spring:message code="contactPhone" /></label>
            <input class="form-control" id="disabledInput" type="text" placeholder="${complaints.getContactPhone()}" disabled>
            <label for="disabledSelect"><spring:message code="contactEmail" /></label>
            <input class="form-control" id="disabledInput" type="text" placeholder="${complaints.getContactEmail()}" disabled>
            <input type="hidden" name="mail" id="mail" value="${complaints.getContactEmail()}">
            <label for="disabledSelect"><spring:message code="createdAt" /></label>
            <input class="form-control" id="disabledInput" type="text" placeholder="${complaints.getFormattedCreatedAt()}" disabled>
            <label for="disabledSelect"><spring:message code="category" /></label>
            <input class="form-control" id="disabledInput" type="text" placeholder="${complaints.getCategory()}" disabled>
            <label for="disabledSelect"><spring:message code="description" /></label>
            <textarea class="form-control" id="disabledInput" type="text" placeholder="${complaints.getDescription()}" disabled></textarea>
            <label for="disabledSelect"><spring:message code="status.current" />: ${complaints.getFullyQualifiedStatus()}</label> 
            <select class="form-control" id="stat" name="stat">
                <option value="${complaints.getStatus()}"><spring:message code="complaintDetails.changeTo" />:</option>
                <option value="OPEN"><spring:message code="status.open" /></option>
                <option value="PROCESSING"><spring:message code="status.processing" /></option>
                <option value="HANDLED"><spring:message code="status.handled" /></option>
            </select>
            <div class="form-group">                                  
                <label><spring:message code="complaintDetails.commentsExternal" /></label>
                <textarea path="commentsExt" id="commentsExt" name="commentsExt" class="form-control" rows="6">${complaints.getCommentsExternal()}</textarea>
                <label><spring:message code="complaintDetails.commentsInternal" /></label>
                <textarea path="commentsInt" id="commentsInt" name="commentsInt" class="form-control" rows="6">${complaints.getCommentsInternal()}</textarea>
            </div>
        </div>  
        <input type="submit" value="Save changes"/>
    </form:form>
    </c:forEach>
</div>
<t:footer/>