
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
    <h2 class="display-6 text-center">
        Добавление маршрута
    </h2>
    <form method="POST"  action="${contextPath}/routes/choose" modelAttribute="routeForm" class="form-signin">

        <div class="form-group">
            <p>Откуда</p>
            <select id="cityA" name="cityA" class="form-control">
                        <c:forEach var="city" items="${cities}">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                </select>
            </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
                <p>Куда</p>
                <select id="cityB" name="cityB" class="form-control">
                    <c:forEach var="city" items="${cities}">
                        <option value="${city.id}">${city.name}</option>
                    </c:forEach>
                </select>
            </div>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Далее</button>
    </form>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
