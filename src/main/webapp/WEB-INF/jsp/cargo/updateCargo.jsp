<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование груза</title>

    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
</head>
<body>

<%@ include file="/WEB-INF/jsp/util/header.jsp" %>

<main role="main" class="container">

    <h2 class="label-primary">Изменить данные о грузе</h2>

    <form:form method="POST" modelAttribute="cargoForm" class="form-signin">
        <%@ include file="/WEB-INF/jsp/cargo/cargoForm.jsp" %>

        <button class="btn btn-lg btn-warning btn-block" type="submit">Изменить</button>
    </form:form>


</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>

</body>
</html>