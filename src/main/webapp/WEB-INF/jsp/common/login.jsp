<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <meta charset="utf-8">
    <title>Вход</title>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/util/header.jsp" %>


<main role="main" class="container-sm text-center">
        <form method="POST" action="${contextPath}/login">
            <br><br>
            <img class="mb-4 mx-auto d-block" src="${contextPath}/resources/images/logo1.png" alt="" width="100" height="100">
            <h1 class="h3 mb-3 fw-normal">Вход</h1>
            <div class="form_wrapper box_shadow">
            <div class="form-floating">
                <input type="text" class="form-control" id="username" name="username" placeholder="Имя пользователя" autofocus>
                <label for="username">Имя пользователя</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password" placeholder="Пароль">
                <label for="password">Пароль</label>
            </div>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="button" class="btn-block btn btn-lg btn-info" onclick="history.back()">Назад</button>
            <button class="btn-block btn btn-lg btn-warning" type="submit">Войти</button>
            </div>
        </form>
</main>



<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>


</body>
</html>
