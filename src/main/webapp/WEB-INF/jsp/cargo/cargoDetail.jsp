<%@ include file="/WEB-INF/jsp/util/baseTabLibs.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/util/baseCss.jsp" %>
    <script src="${contextPath}/resources/js/alert.js"></script>
    <title>
        ${cargo.name}
    </title>
</head>
<body class="d-flex flex-column h-100">
<%@ include file="/WEB-INF/jsp/util/header.jsp" %>
<main role="main" class="container-fluid box_shadow mt-5">
    <h2 class="">Название: ${cargo.name}</h2>
    <hr>
    <p class="lead">Тип груза: ${cargo.cargoType.type}</p>
    <p>Масса: ${cargo.weight}</p>

</main>

<%@ include file="/WEB-INF/jsp/util/footer.jsp" %>
</body>

</html>
