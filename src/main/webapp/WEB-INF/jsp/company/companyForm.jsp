
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="Название компании"
                        autofocus="true"/>
            <form:errors path="name"/>
        </div>
        <span style="color:red" >
                ${message}
        </span>
    </spring:bind>
    <spring:bind path="email">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input path="email" type="text" class="form-control" placeholder="Почта" autofocus="true"/>
            <form:errors path="email"/>
        </div>
    </spring:bind>
    <spring:bind path="description">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="description" class="form-control" placeholder="Описание"
                        autofocus="true"/>
            <form:errors path="description"/>
        </div>
    </spring:bind>
    <spring:bind path="price_kg">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="number" step="0.1" path="price_kg" class="form-control" placeholder="Тариф ($/кг)"
                        autofocus="true"/>
            <form:errors path="price_kg"/>
        </div>
    </spring:bind>
    <spring:bind path="price_km">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="number" step="0.1" path="price_km" class="form-control" placeholder="Тариф ($/км)"
                        autofocus="true"/>
            <form:errors path="price_km"/>
        </div>
    </spring:bind>
</div>
