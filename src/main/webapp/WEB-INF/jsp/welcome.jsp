<%--
  Created by IntelliJ IDEA.
  User: Professional
  Date: 18.10.2021
  Time: 0:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">



    <sec:authentication var="userLogin" property="principal" />

    <sec:authorize access="!isAuthenticated()">
        <h2>Welcome page | <a href="${contextPath}/login" class="nav-link px-2 text-white">Login</a></h2>
    </sec:authorize>

    <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')">
        <h2>Welcome ${pageContext.request.userPrincipal.name} |
            <a href="${contextPath}/admin" class="nav-link px-2 text-white">Admin page</a> |
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </sec:authorize>

    <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_CARRIER')">
        <h2>Welcome ${pageContext.request.userPrincipal.name} |
            <a href="${contextPath}/carrier" class="nav-link px-2 text-white">Carrier page</a> |
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </sec:authorize>

    <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_CUSTOMER')">
        <h2>Welcome ${pageContext.request.userPrincipal.name} |
            <a href="${contextPath}/customer" class="nav-link px-2 text-white">Customer page</a> |
            <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
    </sec:authorize>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>