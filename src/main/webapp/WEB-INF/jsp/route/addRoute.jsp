
<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <title>Новый маршрут</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container-sm">
    <h2 class="display-6 text-center pt-5">
        Добавление маршрута
    </h2>
    <div class="form_wrapper box_shadow">
    <a href="${contextPath}/routes/cities/add"  class="btn btn-warning me-2">Добавить город</a>
    <form:form method="POST" modelAttribute="routeForm" >

        <%@ include file="/WEB-INF/jsp/route/routeForm.jsp" %>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Сохранить</button>
    </form:form>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
