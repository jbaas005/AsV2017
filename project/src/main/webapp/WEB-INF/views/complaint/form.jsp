<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:header/>
<div class="row">
    <div class="col-md-offset-2 col-md-8">
        <div class="row">
            <form:form cssClass="form-horizontal" commandName="complaint" action="${pageContext.request.contextPath}/complaint" method="post" enctype="multipart/form-data">
                <div class="panel panel-default">
                    <div class="panel-heading"><strong><spring:message code="companyInformation" /></strong></div>
                    <div class="panel-body">
                        <spring:bind path="customer.customerId">
                            <div class="form-group form-group-lg${status.error ? ' has-error' : '' }" id="customerIdGroup">
                                <label class="col-md-4 control-label" for="customerId"><spring:message code="customerID" /> *</label>
                                <div class="col-md-4">
                                    <div class="input-group input-group-lg" style="margin-top:10px;">
                                        <spring:message code="customerID" var="customerID" />
                                        <form:input path="customer.customerId" id="customerId" cssClass="form-control" placeholder="${customerID}" style="height:39px;padding:5px 14px;" />
                                        <span class="input-group-btn">
                                            <button class="btn btn-default btn-lg" type="button" id="searchCustomerButton" style="height:39px;padding:7px 16px;"><spring:message code="searchBtn" /></button>
                                        </span>
                                    </div>
                                    <p class="help-block" id="customerIdMessage"><form:errors path="customer.customerId" cssClass="error" /></p>
                                </div>
                            </div>
                        </spring:bind>
                        <t:input path="customer.companyName" id="companyName" />
                        <t:input path="customer.companyAddress" id="companyAddress"/>
                        <t:input path="customer.companyPostalCode" id="companyPostalCode"/>
                        <t:input path="customer.companyCity" id="companyCity"/>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading"><strong><spring:message code="contact" /></strong></div>
                    <div class="panel-body">
                        <t:input path="contactName" required="true" id="contactName"/>
                        <t:input path="contactLastName" required="true" id="contactLastName"/>
                        <t:input path="contactPhone" required="true" id="contactPhone"/>
                        <t:input path="contactEmail" required="true" id="contactEmail"/>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading"><strong><spring:message code="complaint" /></strong></div>
                    <div class="panel-body">
                        <spring:bind path="category">
                            <div class="form-group${status.error ? ' has-error' : '' }">
                                <label class="col-md-4 control-label" for="category"><spring:message code="complaint.category" /></label>
                                <div class="col-md-4">
                                    <form:select cssClass="form-control" path="category">
                                        <option value=" " selected="selected"> </option>
                                        <option value="0"><spring:message code="complaint.category.administration" /></option>
                                        <option value="1"><spring:message code="complaint.category.quotation" /></option>
                                        <option value="2"><spring:message code="complaint.category.employee" /></option>
                                        <option value="3"><spring:message code="complaint.category.delivery" /></option>
                                        <option value="4"><spring:message code="complaint.category.order" /></option>
                                        <option value="5"><spring:message code="complaint.category.other" /></option>
                                    </form:select>
                                    <p class="help-block"><form:errors path="category" cssClass="error" /></p>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="description">
                            <div class="form-group${status.error ? ' has-error' : '' }">
                                <label class="col-md-4 control-label" for="description"><spring:message code="description" /> *</label>
                                <div class="col-md-4">
                                    <spring:message code="description.placeholder" var="descriptionPlaceholder" />
                                    <form:textarea cssClass="form-control" path="description" style="width:450px;height:250px;" placeholder="${descriptionPlaceholder}" />
                                    <p class="help-block"><form:errors path="description" cssClass="error" /></p>
                                </div>
                            </div>
                        </spring:bind>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="priority"><spring:message code="priority" /></label>
                            <div class="col-md-4">
                                <form:select cssClass="form-control" path="priority" cssErrorClass="error">
                                    <option value="1"><spring:message code="priority.normal" /></option>
                                    <option value="2"><spring:message code="priority.high" /></option>
                                    <option value="3"><spring:message code="priority.urgent" /></option>
                                </form:select>
                            </div>
                        </div>    
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="attachment"><spring:message code="attachment" /></label>
                            <div class="col-md-4">
                                <input id="attachment" name="attachment" class="input-file" type="file">
                                <p class="help-block"><spring:message code="complaint.form.attachment.help" /></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <spring:message code="complaint.form.requiredHelp" />
                    <button id="submitButton" type="submit" class="btn btn-warning pull-right"><spring:message code="submit" /></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<t:footer/>
