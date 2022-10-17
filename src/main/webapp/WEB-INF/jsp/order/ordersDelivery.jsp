<%@include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Маршруты</title>
    <%@include file="/WEB-INF/jsp/util/baseCss.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container">
        <h1 class="display-4 text-center">Оформление заказа</h1>
        <p>Маршрут: ${cityA} - ${cityB}</p>
        <p>Вместимость транспорта: ${weight} кг</p>
        <p>Масса товара: ${totalWeight} кг</p>
        <p>Общая сумма: ${totalCost} $</p>
        <p>Транспорт: ${transportId}</p>
        <sec:authorize access="hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CARRIER')">

            <%-- <a class="btn btn-warning addButton" href="${contextPath}/orders/add">Оформить заказ</a>--%>

        </sec:authorize>

        <div class="row">
            <c:forEach var="order" items="${orders}">
                <div class="col-sm-6">
                    <div class="card bg-light" style="margin-bottom: 10px;">
                        <div class="card-body ">
                            <h5 class="card-title ">${order.cargo.name}</h5>

                            <p class="card-text">${order.cityA.name}-${order.cityB.name}</p>
                            <p class="card-text">${order.cost}$ </p>
                            <p class="card-text">${order.cargo.weight} кг</p>
                            <div class="d-block gap-2 mt-2">
                                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                    <a href="${contextPath}/orders/delete/${order.id}" class="btn btn-danger"
                                       onclick="return confirm('Вы уверены?')">Удалить</a>
                                </sec:authorize>
                            </div>

                        </div>
                    </div>
                </div>
                <br>
            </c:forEach>
        </div>
        <button type="button" class="btn-block btn btn-lg btn-info" href="${contextPath}/">ОК</button>
</main>
<%@include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
