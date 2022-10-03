
<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <title>Новая компания</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container-sm">
    <h2 class="display-6 text-center pt-5">
        Добавление компании
    </h2>
    <div class="box_shadow form_wrapper">

    <form:form method="POST" modelAttribute="companyForm">

        <%@ include file="/WEB-INF/jsp/company/companyForm.jsp" %>
        <button type="button" class="btn-block btn btn-lg btn-info" onclick="history.back()">Назад</button>
        <button class="btn btn-lg btn-warning btn-block" type="submit">Сохранить</button>
    </form:form>
    </div>

</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>
</html>
