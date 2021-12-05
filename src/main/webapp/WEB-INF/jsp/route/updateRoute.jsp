<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование маршрута</title>

    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container">

    <h2 class="label-primary">Изменить маршрут</h2>

    <form:form method="POST" modelAttribute="routeForm" class="form-signin">
<%--        <%@ include file="/WEB-INF/jsp/route/routeForm.jsp" %>--%>


        <spring:bind path="city_a">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select id="city_a" path="city_a" class="form-control">
                    <%--                <form:option value="0" label="Город"/>--%>
                    <form:options items="${cities}"
                                  itemLabel="name"
                                  itemValue="id"/>
                </form:select>
                <form:errors path="city_a"/>
            </div>
        </spring:bind>


        <spring:bind path="cities">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select id="cities" path="cities" class="form-control">
                    <%--                <form:option value="0" label="Город"/>--%>
                    <form:options items="${cities}"
                                  itemLabel="name"
                                  itemValue="id"/>
                </form:select>
                <form:errors path="cities"/>
            </div>
        </spring:bind>
<%--        <a href="${contextPath}/routes/update"  class="btn btn-outline-dark">Добавить город</a>--%>
        <c:forEach var="city" items="${routeForm.cities}">
            <h5>${city.name}</h5>
            <%--<a href="${contextPath}/routes/update"  class="btn btn-outline-dark">Добавить город</a>--%>
            <spring:bind path="cities">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select id="cities" path="cities" class="form-control">
                        <%--                <form:option value="0" label="Город"/>--%>
                        <form:options items="${cities}"
                                      itemLabel="name"
                                      itemValue="id"/>
                    </form:select>
                    <form:errors path="cities"/>
                </div>
            </spring:bind>
        </c:forEach>




        <spring:bind path="city_b">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:select id="city_b" path="city_b" class="form-control">
                    <%--                <form:option value="0" label="Город"/>--%>
                    <form:options items="${cities}"
                                  itemLabel="name"
                                  itemValue="id"/>
                </form:select>
                <form:errors path="city_b"/>
            </div>
        </spring:bind>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Название маршрута"
                            autofocus="true"/>
                <form:errors path="name"/>
            </div>
            <span style="color:red" >
                    ${message}
            </span>
        </spring:bind>
        <spring:bind path="distance">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="number" step="0.1" path="distance" class="form-control" placeholder="Расстояние"
                            autofocus="true"/>
                <form:errors path="distance"/>
            </div>
        </spring:bind>






        <p>${routeForm.city_a.name} -
            <a href="${contextPath}/routes/update"  class="btn btn-outline-dark">Добавить город</a> -
            <c:forEach var="city" items="${routeForm.cities}">
                ${city.name} - <a href="${contextPath}/routes/update"  class="btn btn-outline-dark">Добавить город</a> -
            </c:forEach>
                ${routeForm.city_b.name}</p>

        <button class="btn btn-lg btn-warning btn-block" type="submit">Изменить</button>
    </form:form>


</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>

</body>
</html>