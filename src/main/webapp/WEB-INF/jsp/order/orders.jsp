<%@include file="/WEB-INF/jsp/util/baseTabLibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Маршруты</title>
    <%@include file="/WEB-INF/jsp/util/baseCss.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/jsp/util/header.jsp"%>

<main role="main" class="container">
    <p class="display-4 text-center">Заказы</p>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')">

        <a class="btn btn-warning addButton" href="${contextPath}/orders/add">Оформить заказ</a>

    </sec:authorize>

    <div class="row">
        <c:forEach var="order" items="${orders}">
            <div class="col-sm-6">
                <div class="card bg-light" style="margin-bottom: 10px;" >
                    <div class="card-body ">
                        <h5 class="card-title ">${order.cargo.name}</h5>

                        <p class="card-text">${order.route.city_a.name}-${order.route.city_b.name}</p>
<%--                        <p class="card-text">${order.cost}$ </p>--%>
                        <div class="d-block gap-2 mt-2">
                            <a href="${contextPath}/routes/${route.id}" class="btn btn-warning">Подробнее</a>
                            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                <a href="${contextPath}/routes/update/${route.id}" class="btn btn-dark ">Изменить</a>
                                <a href="${contextPath}/routes/delete/${route.id}" class="btn btn-danger" onclick="return confirm('Вы уверены?')">Удалить</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
</main>
<%@include file="/WEB-INF/jsp/util/footer.jsp"%>
</body>
</html>
