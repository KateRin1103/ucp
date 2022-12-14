<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="imageRef" scope="session" value="https://thumbs.dreamstime.com/b/no-image-available-icon-photo-camera-flat-vector-illustration-132483141.jpg"/>

<%--<c:if test="${user.image ne ''}">
    <c:set var="imageRef" scope="session" value="data:image/jpg;base64,${user.image}"/>
</c:if>--%>


<div class="row ">

    <div class="col-lg-4 col-md-4 col-sm-12 justify-content-center">
        <div style="float: right">

            <h1>Фото: </h1>

            <img id="output"
                 width="300px"
                 height="300px"
                 alt="picture"
                 src="${imageRef}"/>

            <%--<spring:bind path="imageUpdate">
                <form:input id="imageUpdate" type="file" path="imageUpdate" onchange="loadFile(event)"
                            disabled="${formDisabled}"/>
                <form:errors path="imageUpdate"/>
            </spring:bind>

            <script>
                const loadFile = function (event) {
                    const output = document.getElementById('output');
                    output.src = URL.createObjectURL(event.target.files[0]);
                    output.onload = function () {
                        URL.revokeObjectURL(output.src)
                    }
                };
            </script>--%>
        </div>
    </div>


    <div class="col-lg-8 col-md-8 col-sm-12 desc">
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="username">Имя пользователя: </label>
                <form:input id="username" type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true" disabled="true"/>
                <form:errors path="username"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="firstName">Имя: </label>
                <form:input id="firstName" type="text" path="firstName" class="form-control" placeholder="Имя"
                            autofocus="true"/>
                <form:errors path="firstName"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="lastName">Фамилия: </label>
                <form:input id="lastName" type="text" path="lastName" class="form-control" placeholder="Фамилия"
                            autofocus="true"/>
                <form:errors path="lastName"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="" for="email">Email: </label>
                <form:input id="email" type="email" path="email" class="form-control" placeholder="Email"
                            autofocus="true"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <h3 class="has-error">
            ${error}
        </h3>
    </div>
</div>