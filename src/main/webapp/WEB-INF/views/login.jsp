<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta charset="utf-8">
    <title>Artrader</title>
    <%@ include file="/WEB-INF/views/tiles/include.jspf" %>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
            ${errorMessage}
    </div>
</c:if>
<div class="container d-flex justify-content-center align-items-center mt-5">
    <div class="card w-50 bg-warning">
        <div class="card-body row">
            <div class="col-6 my-auto mx-auto text-center">
                <span class="h1 navbar-brand logo-l">Artrader</span>
            </div>
            <div class="col-6">
            <h5 class="card-title text-center">Welcome</h5>
            <form:form method="post"
                       action="${pageContext.request.contextPath}/user/loginPost" modelAttribute="user">
            <div class="form-group">
                <label for="email">Email</label>
                <form:input type="email" class="form-control" id="email" path="email" placeholder="Enter email"></form:input>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input type="password" class="form-control" id="password" path="password" placeholder="Password"></form:input>
            </div>
            <div class="form-group row mb-0 mt-4">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
                <div class="col text-right">
                    <a class="btn btn-success" href="${pageContext.request.contextPath}/user/signup" role="button">Sign up</a>
                </div>
            </div>
            </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>


