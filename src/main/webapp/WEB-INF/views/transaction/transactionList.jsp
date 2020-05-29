<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="container mt-3">
    <!-- dropdown box -->

    <!-- list -->
    <div class="card-deck w-100">
        <c:forEach items="${transactionVos }" var="transactionVo">
            <div class="card">
                <div class="card-header">
                        <div class="row">
                            <div class="col">
                                <h5>ORDER PLACED</h5>
                            </div>
                            <div class="col">
                                <h5>TOTAL PRICE</h5>
                            </div>
                            <div class="col">
                                <p class="text-muted float-right">Order #: ${transactionVo.transactionId}</p>
                            </div>
                        </div>
                        <div class="row mb-0">
                            <div class="col">
                                    ${transactionVo.createdTimestamp}
                            </div>
                            <div class="col">
                                <h5 class="card-title text-danger">$ ${transactionVo.price}</h5>
                            </div>
                            <div class="col"><!--occupier--></div>
                        </div>
                </div>

                <div class="card-body">
                    <div class="row">
                        <div class="col-3">
                            <img class="card-img" src="data:image/jpg;base64,${transactionVo.artwork.photo}">
                        </div>
                        <div class="col-7">
                            <h5 class="card-title" style="color: #007bff;"> ${transactionVo.artwork.name}</h5>
                            <p class="card-text">Created by: ${transactionVo.artist.name}</p>
                            <p class="card-text">Bought by: ${transactionVo.buyer.name}</p>
                            <p class="card-text">Sold by: ${transactionVo.seller.name}</p>
                            <p class="card-text">Shipping Status: ${transactionVo.shipping ? "In shipping" : "Completed"}</p>
                        </div>
                    </div>
                </div>
                <div class="card-footer empty">
                    <a href="${pageContext.request.contextPath}/transaction/${transactionVo.transactionId}" class="float-right">See details</a>
                </div>
            </div>
            <div class="w-100 mt-4"></div>
        </c:forEach>
    </div>
    <!-- empty list -->
    <c:if test="${transactionVos == null || fn:length(transactionVos) == 0}">
        <div class="row">
            <div class="alert alert-warning mr-3 w-100" role="alert">
                <h4 class="alert-heading">Whoops, no matches</h4>
                <p>Go browsing all the artworks and make your first order!</p>
                <hr>
                <p class="mb-0">go to <a href="${pageContext.request.contextPath}/sale/search" class="alert-link">Browse artworks</a></p>
            </div>
        </div>
    </c:if>
</div>