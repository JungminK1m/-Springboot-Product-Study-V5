<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="container">
            <table class="table table-striped mt-4">
                <thead>
                    <tr>
                        <th>상품 번호</th>
                        <th>상품 이름</th>
                        <th>상품 가격</th>
                        <th>상품 재고</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${productList}" var="p" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><a href="/product/${p.productId}">${p.productName}</a></td>
                            <td>${p.productPrice}원</td>
                            <td>${p.productQty}개</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%-- <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <li class="page-item ${pagingVO.page == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="${pagingVO.page == 1 ? '#' : '/product?page=' + (pagingVO.page - 1)}">이전</a>
                </li>
                <c:forEach begin="${pagingVO.startPage}" end="${pagingVO.endPage}" var="num">
                    <li class="page-item ${pagingVO.page == num ? 'active' : ''}">
                        <a class="page-link" href="/product?page=${num}">${num}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${pagingVO.page == pagingVO.realEndPage ? 'disabled' : ''}">
                    <a class="page-link" href="${pagingVO.page == pagingVO.realEndPage ? '#' : '/product?page=' + (pagingVO.page + 1)}">다음</a>
                </li>
            </ul>
        </nav> --%>
        <%@ include file="../layout/footer.jsp" %>