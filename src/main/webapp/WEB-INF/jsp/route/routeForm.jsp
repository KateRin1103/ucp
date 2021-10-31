
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <spring:bind path="city_a">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select id="city_a" path="city_a" class="form-control">
                <form:option value="0" label="Город"/>
                <form:options items="${cities}"
                              itemLabel="name"
                              itemValue="id"/>
            </form:select>
            <form:errors path="city_a"/>
        </div>
    </spring:bind>
    <spring:bind path="city_b">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select id="city_b" path="city_b" class="form-control">
                <form:option value="0" label="Город"/>
                <form:options items="${cities}"
                              itemLabel="name"
                              itemValue="id"/>
            </form:select>
            <form:errors path="city_b"/>
        </div>
    </spring:bind>
    <spring:bind path="name">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="name" class="form-control" placeholder="Название маршрута"
                        autofocus="true"/>
            <form:errors path="name"/>
        </div>
        <span style="color:red" >
                ${message}
        </span>
    </spring:bind>
    <spring:bind path="distance">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="number" step="0.1" path="distance" class="form-control" placeholder="Расстояние"
                        autofocus="true"/>
            <form:errors path="distance"/>
        </div>
    </spring:bind>

</div>
