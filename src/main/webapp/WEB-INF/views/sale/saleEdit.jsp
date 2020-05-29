<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <form:form method="post" action="${pageContext.request.contextPath}/sale/${sale.saleId}" modelAttribute="sale">
    <input type="hidden" name="_method" value="put"/>
    <div class="card">
        <h5 class="card-header">Edit Sale Details</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-5">
                    <img class="card-img" src="data:image/jpg;base64,${sale.artwork.photo}">
                </div>
                <div class="col-7">
                    <h5 class="card-title"><small>Artwork name: </small>${sale.artwork.name}</h5>
                    <p class="card-text">Creator name: ${sale.artist.name}</p>
                    <p class="card-text">Date of creation: ${sale.artwork.date} </p>
                    <p class="card-text">Category: ${sale.artwork.category}</p>
                    <div class="form-group row">
                        <label for="price" class="col-sm-2 col-form-label">Price: </label>
                        <div class="col-sm-10">
                            <form:input type="text" class="form-control" id="price" path="price" placeholder="Enter price" required="required"></form:input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <h5 class="card-title">Description</h5>
                <p class="card-text">${sale.artwork.description}</p>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Edit</button>
                <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </div>
    </form:form>
</div>
