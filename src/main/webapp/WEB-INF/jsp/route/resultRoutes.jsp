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
    <h4>Груз</h4>
    <p>${cargo.name} ${cargo.weight} ${cargo.cargoType.type}</p>

        <c:forEach items="${routes}" var="map">
            <form:form method="POST"  class="form-signin" action="/orders/add" name="addOrder"> <h2>${map.key.name}</h2>
            <c:forEach items="${map.value}" var="route">
                <h5>Маршрут: ${route.value}км</h5>
                <c:forEach var="city" items="${route.key}">
                    <p>${city.name}</p>
                </c:forEach>
                <input type="number" name="cityA" value="${cityA.id}" hidden>
                <input type="number" name="cityB" value="${cityB.id}" hidden>
                <input type="number" name="company" value="${map.key.id}" hidden>
                <input type="number" name="cargo" value="${cargo.id}" hidden>
                <input type="number" name="distance" value="${route.value}" hidden>
                <button class="btn btn-lg btn-warning btn-block" type="submit">Добавить</button>
            </c:forEach>
            </form:form>
        </c:forEach>

</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
