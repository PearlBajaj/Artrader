<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <div class="card">
        <h5 class="card-header">Change Your Information</h5>
        <div class="card-body">
        <form:form method="post"
                   action="${pageContext.request.contextPath}/user/edit" modelAttribute="user">
            <form:input type="hidden" class="form-control" id="userId" path="userId"></form:input>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                    <form:input type="email" class="form-control" id="email" path="email" placeholder="Email" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <form:input type="password" class="form-control" id="password" path="password" placeholder="Password" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Name</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="name" path="name" placeholder="name" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="mobileNumber" class="col-sm-2 col-form-label">Mobile Number</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="mobileNumber" path="mobileNumber" placeholder="Mobile number" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="address" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="address" path="address" placeholder="1234 Main St, Darlington, NSW" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Edit</button>
                </div>
                <div class="col text-right">
                    <a class="btn btn-light" href="${pageContext.request.contextPath}/user/edit" role="button">Reset</a>
                </div>
            </div>
        </form:form>
        </div>
    </div>
</div>