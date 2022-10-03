<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <script src="${contextPath}/resources/js/alert.js"></script>
    <title>
        ${route.cityA}-${route.cityB}
    </title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>
<main role="main" class="container">
    <h1 class="display-3">${route.name}</h1>

    <h5>Начальная точка: ${route.cityA.name}</h5>
    <p>${route.cityA.name} -
    <c:forEach var="city" items="${route.cities}">
        ${city.name} -
    </c:forEach>
    ${route.cityB.name}</p>
    <h5>Конечная точка: ${route.cityA.name}</h5>
    <p>Расстояние: ${route.distance} км</p>

</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>

</html>
