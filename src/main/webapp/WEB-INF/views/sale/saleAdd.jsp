<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
    <form method="post" action="${pageContext.request.contextPath}/sale/add">
    <div class="card">
        <h5 class="card-header">Sell Your Collection</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-5">
                    <img class="card-img" src="data:image/jpg;base64,${artwork.photo}">
                </div>
                <div class="col-7">
                    <h5 class="card-title"><small>Artwork name: </small>${artwork.name}</h5>
                    <p class="card-text">Date of creation: ${artwork.date} </p>
                    <p class="card-text">Category: ${artwork.category}</p>
                    <div class="form-group row">
                        <label for="price" class="col-sm-2 col-form-label">Price: </label>
                        <div class="col-sm-10">
                            <input type="hidden" name="artworkId" name="artworkId" value="${artwork.artworkId}"></input>
                            <input type="text" class="form-control" id="price" name="price" placeholder="Enter price"></input>
                        </div>
                    </div>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <h5 class="card-title">Description</h5>
                <p class="card-text">${artwork.description}</p>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Sell</button>
                <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary">Cancel</a>
            </div>
        </div>
    </div>
    </form>
</div>
