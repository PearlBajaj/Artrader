<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${model.pagination != null}">
<nav aria-label="Page navigation example" class="mt-3">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <button class="page-link" onclick="changePage(${model.pagination.firstPage})" aria-label="First">
                <span aria-hidden="true">&laquo;</span>
            </button>
        </li>
        <li class="page-item">
            <button class="page-link" onclick="changePage(${model.pagination.prevPage})" aria-label="Previous">
                <span aria-hidden="true">&lt;</span>
            </button>
        </li>
        <c:forEach begin="${model.pagination.startPage}" end="${model.pagination.endPage}" varStatus="index">
            <c:choose>
                <c:when test="${index.current == model.pagination.currPage}">
                    <li class="page-item active">
                        <button class="page-link" onclick="changePage(${index.current})">${index.current}
                            <span class="sr-only">(current)</span>
                        </button>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <button class="page-link" onclick="changePage(${index.current})">${index.current}</button>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="page-item">
            <button class="page-link" onclick="changePage(${model.pagination.nextPage})" aria-label="Next">
                <span aria-hidden="true">&gt;</span>
            </button>
        </li>
        <li class="page-item">
            <button class="page-link" onclick="changePage(${model.pagination.lastPage})" aria-label="Last">
                <span aria-hidden="true">&raquo;</span>
            </button>
        </li>
    </ul>
</nav>
</c:if>