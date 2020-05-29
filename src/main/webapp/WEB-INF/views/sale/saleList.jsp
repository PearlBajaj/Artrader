<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-3">
    <div class="row mb-3">
        <div class="text-right col">
            <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/artwork/collections" role="button">Sell Artwork</a>
        </div>
    </div>
        <c:forEach items="${model.sales}" var="sale">
        <div class="card mb-3">
            <div class="card-header">
                <h5>PRODUCT PLACED<small class="text-muted float-right">Product #: ${sale.artwork.artworkId}</small></h5>
                ${sale.modifiedTimestamp}
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-3">
                        <img src="data:image/jpg;base64,${sale.artwork.photo}" class="card-img" alt="${sale.artwork.name}">
                    </div>
                    <div class="col-7">
                        <h5 class="card-title">${sale.artwork.name}</h5>
                        <p class="card-text">Creator: ${sale.artist.name}</p>
                        <p class="card-text">Category: ${sale.artwork.category}</p>
                        <p class="card-text text-danger">$ ${sale.price}</p>
                    </div>
                    <div class="col">
                        <a href="${pageContext.request.contextPath}/sale/${sale.saleId}" class="btn btn-outline-secondary btn-block">Detail/Edit</a>
                        <br>
                        <form method="post" action="${pageContext.request.contextPath}/sale/${sale.saleId}">
                            <input type="hidden" name="_method" value="delete"/>
                            <button type="submit" class="btn btn-outline-secondary btn-block">Stop Selling</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>

    <!-- empty list -->
    <c:if test="${model.sales == null || fn:length(model.sales) == 0}">
    <div class="row">
        <div class="alert alert-warning mr-3 w-100" role="alert">
            <h4 class="alert-heading">Whoops, no matches</h4>
            <p>You can be the first person who publishes artworks here!</p>
            <hr>
            <p class="mb-0">go to <a href="${pageContext.request.contextPath}/artwork/add" class="alert-link">publish artworks</a></p>
        </div>
    </div>
    </c:if>
</div>