<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<header class="p-1 bg-info text-white" >
    <div >
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

           <a style="margin-left: 50px" href="${contextPath}/">
               <img src="${contextPath}/resources/images/logo1.png"  width="80" height="80">
           </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">

                <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_CARRIER')">
                <li><a href="${contextPath}/routes" class="nav-link px-2 text-white">Маршруты</a></li>
                <li><a href="${contextPath}/companies/myCompany" class="nav-link px-2 text-white">Моя компания</a></li>
                </sec:authorize>
<%--                <li><a href="${contextPath}/companies" class="nav-link px-2 text-white">Компании</a></li>--%>
                <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')|| hasAuthority('ROLE_CARRIER')">
                <li><a href="${contextPath}/cargos" class="nav-link px-2 text-white">Грузы</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')|| hasAuthority('ROLE_CARRIER')">
                    <li><a href="${contextPath}/orders" class="nav-link px-2 text-white">Заказы</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')|| hasAuthority('ROLE_CARRIER')">
                    <li><a href="${contextPath}/knapsack" class="nav-link px-2 text-white">Оформление доставки</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                <li><a href="${contextPath}/cargos/add" class="nav-link px-2 text-white">Оставить заявку</a></li>
                </sec:authorize>

            </ul>


            <sec:authentication var="userLogin" property="principal" />

            <sec:authorize access="isAuthenticated() && !hasAuthority('ROLE_ADMIN')">
                <div class="dropdown text-end" style="margin-right: 50px;">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">

                        <img src="./resources/images/user.png" alt="userImg" width="50" height="50" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li><a class="dropdown-item" href="#">Логин: ${userLogin.username}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${contextPath}/personal-cabinet">Профиль</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/logout">Выйти</a></li>
                    </ul>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')">
                <div class="dropdown text-end" style="margin-right: 50px;">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">

                        <img src="/resources/images/user.png" alt="userImg" width="50" height="50" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li><a class="dropdown-item" href="#">Логин: ${userLogin.username}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${contextPath}/users">Управление пользователями</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/personal-cabinet">Профиль</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/logout">Выйти</a></li>
                    </ul>
                </div>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <div class="text-end" style="margin-right: 50px;">

                    <a href="${contextPath}/login"  class="btn btn-outline-light me-2">Войти</a>
                    <a href="${contextPath}/registration" class="btn btn-warning">Зарегистрироваться</a>
                </div>
            </sec:authorize>



        </div>
    </div>
</header>
