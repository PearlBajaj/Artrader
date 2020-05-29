<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-3">
    <div class="row ml-0 mb-3">
        <div class="dropdown">
            <btn class="btn btn-outline-secondary dropdown-toggle" role="button" id="dropdownCategoryLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Category:
            </btn>
            <div class="dropdown-menu" aria-labelledby="dropdownCategoryLink">
                <a class="dropdown-item" onclick="changeCategory('All')">All</a>
                <a class="dropdown-item" onclick="changeCategory('Painting')">Painting</a>
                <a class="dropdown-item" onclick="changeCategory('Sculpture')">Sculpture</a>
                <a class="dropdown-item" onclick="changeCategory('Ceramics')">Ceramics</a>
                <a class="dropdown-item" onclick="changeCategory('Photography')">Photography</a>
            </div>
        </div>

        <div class="text-right col">
            <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/artwork/add" role="button">Publish Artwork</a>
        </div>
    </div>
    <!-- list -->
    <div class="card-deck">
        <c:forEach items="${model.artworks}" var="artwork" varStatus="loop">
        <div class="card" style="width: 18rem;">
            <img src="data:image/jpg;base64,${artwork.photo}" class="card-img-top" alt="${artwork.name}">
            <div class="card-body">
                <h5 class="card-title">${artwork.name}</h5>
                <p class="card-text">${artwork.date}</p>
                <p class="card-text">${artwork.category}</p>
                <p class="card-text text-black-50 text-truncate-3">${artwork.description}</p>
                <p class="card-text text-danger">Total Revenue: $ ${artwork.totalRevenue}</p>
            </div>
            <div class="card-footer empty">
                <a href="${pageContext.request.contextPath}/artwork/${artwork.artworkId}" class="float-right">See details</a>
            </div>
        </div>
        <c:if test="${loop.count%4 == 0}">
        <div class="w-100 mt-4"></div>
        </c:if>
        </c:forEach>
    </div>
    <!-- empty list -->
    <c:if test="${model.artworks == null || fn:length(model.artworks) == 0}">
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