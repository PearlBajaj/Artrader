<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-3">
    <!-- list -->
    <div class="card-deck">
        <c:forEach items="${favVo }" var="favVo" varStatus="loop">
            <div class="card" style="width: 18rem;">
                <img src="data:image/jpg;base64,${favVo.artwork.photo}" class="card-img-top" alt="${favVo.artwork.name}">
                <div class="card-body">
                    <p class="card-text">${favVo.artist.name}</p>
                    <p class="card-text">${favVo.artwork.category}</p>
                    <p class="card-text text-black-50 text-truncate-3">${favVo.artwork.description}</p>
                </div>
                <div class="card-footer empty">
                    <a href="${pageContext.request.contextPath}/artwork/${favVo.artwork.artworkId}/favourite" class="float-right">See details</a>
                </div>
            </div>
            <c:if test="${loop.count%4 == 0}">
                <div class="w-100 mt-4"></div>
            </c:if>
        </c:forEach>
    </div>
    <!-- empty list -->
    <c:if test="${favVo == null || fn:length(favVo) == 0}">
        <div class="row">
            <div class="alert alert-warning mr-3 w-100" role="alert">
                <h4 class="alert-heading">Whoops, no matches</h4>
                <p>Go browsing all the artworks and add some to favourites!</p>
                <hr>
                <p class="mb-0">go to <a href="${pageContext.request.contextPath}/sale/search" class="alert-link">Browse artworks</a></p>
            </div>
        </div>
    </c:if>
</div>