<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-3">
	<div class="row mb-3">
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
	</div>
	<!-- list -->
	<div class="card-deck">
		<c:forEach items="${model.sales}" var="sale" varStatus="loop">
			<div class="card ml-0" style="width: 18rem;">
				<c:choose>
				<c:when test="${sale.artwork.photo == null || sale.artwork.photo == ''}">
				<img src="<c:url value="/resources/static/img/default-artwork.png"/>" class="card-img-top" alt="${sale.artwork.name}">
				</c:when>
				<c:otherwise>
				<img src="data:image/jpg;base64,${sale.artwork.photo}" class="card-img-top" alt="${sale.artwork.name}">
				</c:otherwise>
				</c:choose>
				<div class="card-body">
					<c:if test="${!sale.favourite}">
					<form action="/artwork/${sale.artwork.artworkId}/favourite" method="post">
					<h5 class="card-title">${sale.artwork.name}
						<small><button class="oi oi-heart float-right" type="submit"></button></small>
					</h5>
					</form>
					</c:if>
					<c:if test="${!!sale.favourite}">
					<form action="/artwork/${sale.artwork.artworkId}/favourite" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<h5 class="card-title">${sale.artwork.name}
							<small><button class="oi oi-heart float-right active" type="submit"></button></small>
						</h5>
					</form>
					</c:if>
					<p class="card-text">${sale.artist.name}</p>
					<p class="card-text">${sale.artwork.category}</p>
				</div>
				<div class="card-footer empty pb-0">
					<h6 class="card-title text-danger">$ ${sale.price}
						<small>
							<a href="${pageContext.request.contextPath}/sale/${sale.saleId}" class="float-right mt-1">See details</a>
						</small>
					</h6>
				</div>
			</div>
			<c:if test="${loop.count%4 == 0}">
				<div class="w-100 mt-4"></div>
			</c:if>
		</c:forEach>
	</div>

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