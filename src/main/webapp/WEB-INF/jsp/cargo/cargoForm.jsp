
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="Название груза"
                        autofocus="true"/>
            <form:errors path="name"/>
        </div>
        <span style="color:red" >
                ${message}
        </span>
    </spring:bind>
    <spring:bind path="cargoType">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select id="cargoType" path="cargoType" class="form-control">
                <form:option value="0" label="Тип груза"/>
                <form:options items="${types}"
                              itemLabel="type"
                              itemValue="id"/>
            </form:select>
            <form:errors path="cargoType"/>
        </div>
    </spring:bind>

    <spring:bind path="weight">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="number" step="0.1" path="weight" class="form-control" placeholder="Вес"
                        autofocus="true"/>
            <form:errors path="weight"/>
        </div>
    </spring:bind>
</div>
