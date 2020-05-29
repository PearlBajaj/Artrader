<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <div class="card">
        <h5 class="card-header">Order Details</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-5">
                    <img class="card-img" src="data:image/jpg;base64,${artworkVo.photo}">
                </div>
                <div class="col-7">
                    <h5 class="card-title"><small>Artwork name: </small>${artworkVo.name}</h5>
                    <p class="card-text">Creator name: ${artworkVo.artist.name}</p>
                    <p class="card-text">Date of creation: ${artworkVo.createdTimestamp} </p>
                    <p class="card-text">Category: ${artworkVo.category}</p>
                    <p class="card-text">Bought by: ${buyer.name}</p>
                    <p class="card-text">Sold by: ${seller.name}</p>
                    <p class="card-text">Order placed: ${transaction.createdTimestamp} </p>
                    <p class="card-text">Shipping Status: ${transaction.shipping ? "In shipping" : "Completed"} </p>
                    <h5 class="card-title text-danger"><small class="text-body">Price: </small>$ ${transaction.price}</h5>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <h5 class="card-title">Description</h5>
                <p class="card-text">${artworkVo.description}</p>
            </div>
            <div class="text-center" >
                <form method="get">
                    <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary"> Back </a>
                </form>
            </div>
        </div>
    </div>
</div>
