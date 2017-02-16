<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:header/>
<div class="row">
    <div class="col-md-12">
        <h3 style="margin-top: 0"><spring:message code="status"/>: <span class="text-muted">${complaint.getFullyQualifiedStatus()}</span></h3>
        <div class="progress">
            <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${100/3*(complaint.status.getValue())}%;">
                <span class="sr-only">${100/3*(complaint.status.getValue())}%</span>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-8">
        <c:if test="${review != null && review.rating == 0}">
            <h3 style="margin-top:0"><spring:message code="status.reviewTitle"/></h3>
            <p><spring:message code="status.reviewMessage"/></p>
            <form:form commandName="review" action="${pageContext.request.contextPath}/review/send" method="post">
                <form:input type="hidden" path="customer" value="${complaint.customer.customerId}"/>
                <form:input type="hidden" path="complaint" value="${complaint.complaintId}"/>
                <div class="form-group">
                    <label class="control-label" style="text-align:left;" for="rating"><spring:message code="rating"/></label>                   
                    <form:input path="rating" type="number" data-min="1" data-max="5" name="rating" id="rating" cssClass="rating" />
                    <form:errors path="rating" />
                </div>
                <div class="form-group">
                    <label class="control-label" style="text-align:left;" for="comment"><spring:message code="status.givenFeedback"/></label>
                    <form:textarea cssClass="form-control" path="comment" cssErrorClass="error" />
                </div>
                <div class="clearfix">
                    <button id="submitButton" type="submit" class="btn btn-warning pull-right"><spring:message code="submit"/></button>
                </div>
            </form:form>
            <hr />
        </c:if>
        <c:if test="${review != null && review.rating != 0}">
            <h2><spring:message code="status.thankYouTitle"/></h2>
            <spring:message code="status.thankYouMessage"/>: ${review.rating}
            <br>
            <i>${review.comment}</i>
            <hr />
        </c:if>
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong><spring:message code="complaint"/></strong></div>
            <div class="panel-body">
                <p>${complaint.description}</p>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong><spring:message code="status.employeeComments"/></strong></div>
            <div class="panel-body">
                <p>${complaint.commentsExternal}</p>
            </div>
        </div>                        
    </div>
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong><spring:message code="complaintInformation"/></strong>
            </div>
            <div class="panel-body">
                <p>
                    <strong><spring:message code="dateSent"/>: </strong> <fmt:formatDate value="${complaint.createdAt}" pattern="dd-MM-yyyy" /> <spring:message code="time.at"/> <fmt:formatDate value="${complaint.createdAt}" pattern="HH:mm:ss" />
                </p>
                <p>
                    <strong><spring:message code="priority"/>: </strong> ${complaint.getStringPriority()}
                </p>
            </div>
            <div class="panel-footer panel-heading">
                <strong><spring:message code="yourContactPerson"/></strong>
            </div>
            <div class="panel-body">
                <p>
                    <strong><spring:message code="fullName"/>: </strong> ${employee.employeeName}
                </p>
                <p>
                    <strong><spring:message code="phone"/>: </strong> ${employee.employeePhone}
                </p>
                <p>
                    <strong><spring:message code="mail"/>: </strong> ${employee.employeeMail}
                </p>
            </div>
        </div>
    </div>
</div>
<t:footer/>
