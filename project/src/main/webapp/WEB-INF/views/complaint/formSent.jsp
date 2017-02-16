<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:header/>
    <div class="row">
        <div class="col-lg-9">
            <h2><spring:message code="formSent.sent"/></h2>
            <h2>
                <spring:message code='formSent.followComplaint' arguments='<a href="/StihoComplaints/complaint/status/${complaint.trackingCode}">'/>
            </h2>
        </div>   
    </div>  
<t:footer/>
