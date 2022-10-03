
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>

    <spring:bind path="cityA">
        <div class="form-group box_shadow form_wrapper ${status.error ? 'has-error' : ''}">
            <form:select id="cityA" path="cityA" class="form-control">
                <form:option value="0" label="Город"/>
                <form:options items="${cities}"
                              itemLabel="name"
                              itemValue="id"/>
            </form:select>
            <form:errors path="cityA"/>
        </div>
    </spring:bind>
    <spring:bind path="cityB">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select id="cityB" path="cityB" class="form-control">
                <form:option value="0" label="Город"/>
                <form:options items="${cities}"
                              itemLabel="name"
                              itemValue="id"/>
            </form:select>
            <form:errors path="cityB"/>
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
            <p>Расстояние</p>
            <form:input type="number" step="0.1" path="distance" class="form-control" placeholder="Расстояние"
                        autofocus="true"/>
            <form:errors path="distance"/>
        </div>
    </spring:bind>

    <spring:bind path="deliveryMethod">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select id="deliveryMethod" path="deliveryMethod" class="form-control">
                <form:option value="0" label="Метод доставки"/>
                <form:options items="${methods}"
                              itemLabel="name"
                              itemValue="id"/>
            </form:select>
            <form:errors path="deliveryMethod"/>
        </div>
    </spring:bind>

</div>
