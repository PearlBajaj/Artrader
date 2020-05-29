<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <form:form method="POST"
               action="${pageContext.request.contextPath}/artwork/add" modelAttribute="artwork" enctype="multipart/form-data">
    <div class="card">
        <h5 class="card-header">Publish Your Artwork!</h5>
        <div class="card-body">
            <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">Artwork Title</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="name" path="name" placeholder="Enter your artwork title" required="required"></form:input>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck1">
                        <label class="form-check-label" for="gridCheck1">
                            I created this artwork.
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="date" class="col-sm-2 col-form-label">Date of Creation</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="createdTimestamp" path="date" placeholder="DD/MM/YYYY" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="category" class="col-sm-2 col-form-label">Category</label>
                <div class="col-sm-10">
                    <form:input type="hidden" class="form-control" id="category" path="category" value="painting"></form:input>
                    <select class="form-control" id="selectCategory" onchange="$('#category').val($(this).val())">
                        <option value="Painting">Painting</option>
                        <option value="Sculpture">Sculpture</option>
                        <option value="Ceramics">Ceramics</option>
                        <option value="Photography">Photography</option>
                    </select>
                </div>
            </div>
            <div class="form-group row">
                <label for="profitRate" class="col-sm-2 col-form-label">Profit Rate</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="profitRate" path="profitRate" placeholder="Set profit rate per resale" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="description" path="description" placeholder="Enter description. Please include the details of your artwork. e.g. size, theme" required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="price" class="col-sm-2 col-form-label">Price</label>
                <div class="col-sm-10">
                    <form:input type="text" class="form-control" id="price" path="price" placeholder="Enter price." required="required"></form:input>
                </div>
            </div>
            <div class="form-group row">
                <label for="photoData" class="col-sm-2 col-form-label">Photo</label>
                <div class="col-sm-10">
                    <form:input type="file" class="form-control" id="photoData" path="photoData" required="required"></form:input>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Publish</button>
            </div>
        </div>
    </div>
    </form:form>
</div>