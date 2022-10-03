
<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <title>Новый груз</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container">
    <h2 class="display-6 text-center pt-5">
        Добавление груза
    </h2>
    <form:form method="POST" modelAttribute="cargoForm">

        <%@ include file="/WEB-INF/jsp/cargo/cargoForm.jsp" %>
        <button type="button" class="btn-block btn btn-lg btn-info" onclick="history.back()">Назад</button>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Далее</button>
    </form:form>
</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
