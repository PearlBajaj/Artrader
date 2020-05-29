<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <div class="card">
        <h5 class="card-header">Sale Details</h5>
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
                    <h5 class="card-title text-danger"><small class="text-body">Price: </small>$ ${sale.price}</h5>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <h5 class="card-title">Description</h5>
                <p class="card-text">${sale.artwork.description}</p>
            </div>
            <div class="text-center">
                <form method="post" action="${pageContext.request.contextPath}/transaction/add">
                    <input type="hidden" name="saleId" value="${sale.saleId}">
                    <button type="submit" class="btn btn-primary">Buy Now</button>
                    <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</div>
