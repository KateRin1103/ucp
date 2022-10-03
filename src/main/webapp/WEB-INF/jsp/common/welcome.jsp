<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 18.10.2021
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome</title>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
</head>
<body class="d-flex flex-column h-100">
<script>
    function changeUrl() {
        console.log('called');
        return window.location.href ='http://localhost:9010/login';
    }
</script>
<%@include file="/WEB-INF/jsp/util/header.jsp"%>
<div class="container">



    <sec:authentication var="userLogin" property="principal" />

    <sec:authorize access="!isAuthenticated()">
        <h2>Welcome</h2>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
    </sec:authorize>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>