<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
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
    <div class="card w-75">
        <h5 class="card-header bg-warning">Sing up</h5>
        <div class="card-body">
            <form:form method="post"
                       action="${pageContext.request.contextPath}/user/add" modelAttribute="user">
            <div class="form-group row">
                <label for="email" class="col-sm-3 col-form-label">Email</label>
                <div class="col-sm-9">
                    <form:input type="email" class="form-control" id="email" path="email" placeholder="Email"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-3 col-form-label">Password</label>
                <div class="col-sm-9">
                    <form:input type="password" class="form-control" id="password" path="password" placeholder="Password"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="name" class="col-sm-3 col-form-label">Name</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" id="name" path="name" placeholder="name"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="mobileNumber" class="col-sm-3 col-form-label">Mobile Number</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" id="mobileNumber" path="mobileNumber" placeholder="Mobile number"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="address" class="col-sm-3 col-form-label">Address</label>
                <div class="col-sm-9">
                    <form:input type="text" class="form-control" id="address" path="address" placeholder="1234 Main St, Darlington, NSW"></form:input>
                </div>
            </div>
            <div class="form-group row mb-0">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
                <div class="col text-right">
                    <a class="btn btn-light" href="${pageContext.request.contextPath}/user/login" role="button">Cancel</a>
                </div>
            </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>