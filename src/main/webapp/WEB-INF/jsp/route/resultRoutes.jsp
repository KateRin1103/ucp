
<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <title>Новый маршрут</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container">


    <c:forEach items="${routes}" var="map">
        <h2>${map.key.name}</h2>
        <c:forEach items="${map.value}" var="route">
            <h5>Маршрут: ${route.key}  ${route.value}км</h5>
            <c:forEach var="city" items="${route.key}">
                <p>${city.name}</p>
            </c:forEach>
        </c:forEach>
    </c:forEach>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
