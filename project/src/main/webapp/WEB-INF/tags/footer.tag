<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
    <hr />
    &copy; 2014-2015
    <a class="pull-right" href="${pageContext.request.contextPath}/faq"><spring:message code="footer.faq" /></a>
</div>
</div>
    <script src="<c:url value="/resources/vendor/jquery/jquery-1.11.1.min.js" />"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/autofill.js" />"></script>
    <script src="<c:url value="/resources/vendor/bootstrap-rating-input.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="<c:url value="/resources/js/list.js" />"></script>
    <script src="<c:url value="/resources/js/complaintSearch.js" />"></script>
    <script>
        $(function(){
            $("#mtable").tablesorter();
        });
        $(function () {
            $('#cdiv').highcharts(${chartJson});
        });
    </script>
</body>
</html>