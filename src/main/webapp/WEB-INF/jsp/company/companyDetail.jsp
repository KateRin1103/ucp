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
        ${company.name}
    </title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>
<main role="main" class="container">
    <c:if test="${company.id==null}">
        <h1 class="display-3">У вас еще нет компании</h1>
        <a href="${contextPath}/companies/add"  class="btn btn-warning me-2">Добавить компанию</a>
    </c:if>
    <c:if test="${company.id>0}">
    <h1 class="display-3">${company.name}</h1>
    <hr>
    <p class="lead">${company.description}</p>
    <p>E-mail: ${company.email}</p>
    <p>Тариф $/кг: ${company.price_kg}</p>
    <p>Тариф $/км: ${company.price_km}</p>
        <p>Маршруты</p>
        <c:forEach var="route" items="${company.routes}">
            <p>${route.name}     ${route.cityA.name} - ${route.cityB.name} (${route.distance}км)  ${route.deliveryMethod.name}</p>
        </c:forEach>
        <a href="${contextPath}/routes/add"  class="btn btn-warning me-2">Добавить маршрут</a>
    </c:if>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>

</html>
