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
    <form:form method="POST" class="form-signin" action="/orders/add" name="addOrder"><h2>${map.key.name}</h2>
       <%-- <h5>Маршрут: ${length}км</h5>--%>
        <c:forEach var="city" items="${routes}">
            <p>${city}</p>
        </c:forEach>
        <input type="number" name="cityA" value="${cityA.id}" hidden>
        <input type="number" name="cityB" value="${cityB.id}" hidden>
        <input type="number" name="cargo" value="${cargo.id}" hidden>
        <input type="number" name="distance" value="${length}" hidden>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Добавить</button>
    </form:form>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
