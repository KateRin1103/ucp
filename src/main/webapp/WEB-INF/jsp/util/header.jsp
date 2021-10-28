<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<header class="p-3 bg-dark text-white" >
    <div >
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

           <a href="${contextPath}/">
               <img src="${contextPath}/resources/images/logo.png" width="50" height="50">
           </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${contextPath}/" class="nav-link px-2 text-white">Главная</a></li>
                <sec:authorize access="isAuthenticated()">
                <li><a href="${contextPath}/parcels" class="nav-link px-2 text-white">Почтовые отправления</a></li>
                </sec:authorize>
                <li><a href="${contextPath}/stores" class="nav-link px-2 text-white">Интернет-магазины</a></li>
                <li><a href="${contextPath}/companies" class="nav-link px-2 text-white">Почтовые службы</a></li>
                <li><a href="${contextPath}/systems" class="nav-link px-2 text-white">Платежные системы</a></li>
                <li><a href="${contextPath}/about" class="nav-link px-2 text-white">О нас</a></li>

            </ul>

            <form:form action="/" class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <input type="search" id="track" name="track" class="form-control form-control-dark" placeholder="Найти..." />
                <input type="submit" hidden />
            </form:form>

            <sec:authentication var="userLogin" property="principal" />

            <sec:authorize access="isAuthenticated() && !hasAuthority('ROLE_ADMIN')">
                <div class="dropdown text-end">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">

                        <img src="/resources/images/user.png" alt="userImg" width="50" height="50" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li><a class="dropdown-item" href="#">Логин: ${userLogin.username}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${contextPath}/statistics">Статистика</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/personal-cabinet">Профиль</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/logout">Выйти</a></li>
                    </ul>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated() && hasAuthority('ROLE_ADMIN')">
                <div class="dropdown text-end">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">

                        <img src="/resources/images/user.png" alt="userImg" width="50" height="50" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1" style="">
                        <li><a class="dropdown-item" href="#">Логин: ${userLogin.username}</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${contextPath}/users">Управление пользователями</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/statistics">Статистика</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/personal-cabinet">Профиль</a></li>
                        <li><a class="dropdown-item" href="${contextPath}/logout">Выйти</a></li>
                    </ul>
                </div>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <div class="text-end">

                    <a href="${contextPath}/login"  class="btn btn-outline-light me-2">Войти</a>
                    <a href="${contextPath}/registration" class="btn btn-warning">Зарегистрироваться</a>
                </div>
            </sec:authorize>



        </div>
    </div>
</header>