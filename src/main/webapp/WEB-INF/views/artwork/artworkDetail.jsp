<div class="container mt-3">
    <div class="card">
        <h5 class="card-header">Artwork Details</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-5">
                    <img class="card-img" src="data:image/jpg;base64,${artwork.photo}">
                </div>
                <div class="col-7">
                    <h5 class="card-title"><small>Artwork name: </small>${artwork.name}</h5>
                    <p class="card-text">Creator name: ${artwork.artist.name}</p>
                    <p class="card-text">Date of creation: ${artwork.date} </p>
                    <p class="card-text">Category: ${artwork.category}</p>
                </div>
            </div>
            <div class="rounded border mt-3 mb-3 p-2">
                <h5 class="card-title">Description</h5>
                <p class="card-text">${artwork.description}</p>
            </div>
            <div class="text-center">
                <a href="${pageContext.request.getHeader("referer")}" class="btn btn-secondary">Close</a>
            </div>
        </div>
    </div>
</div>