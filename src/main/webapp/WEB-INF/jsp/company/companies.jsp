<%@include file="/WEB-INF/jsp/util/baseTabLibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Компании</title>
    <%@include file="/WEB-INF/jsp/util/baseCss.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/jsp/util/header.jsp"%>

<main role="main" class="container">
    <p class="display-4 text-center">Компании</p>
    <sec:authorize access="hasAuthority('ROLE_ADMIN')">

        <a class="btn btn-warning addButton" href="${contextPath}/companies/add">Добавить новую компанию</a>

    </sec:authorize>

    <div class="row">
        <c:forEach var="company" items="${companies}">
            <div class="col-sm-6">
                <div class="card bg-light" style="margin-bottom: 10px;" >
                    <div class="card-body ">
                        <h5 class="card-title ">${company.name}</h5>
                        <a href="#" class="card-text">${company.email}</a>
                        <br>
                        <div class="d-block gap-2 mt-2">
                            <a href="${contextPath}/companies/${company.id}" class="btn btn-warning">Подробнее</a>
                            <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                <a href="${contextPath}/companies/update/${company.id}" class="btn btn-dark ">Изменить</a>
                                <a href="${contextPath}/companies/delete/${company.id}" class="btn btn-danger" onclick="return confirm('Вы уверены?')">Удалить</a>
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
