<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="text-center m-4">
            <h1>유저 구매목록 페이지</h1>
        </div>
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>유저이름</th>
                        <th>구매 상품명</th>
                        <th>상품가격</th>
                        <th>구매수량</th>
                        <th>구매시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orderedList}" var="op" varStatus="status">
                        <tr>

                            <td>${status.count}</td>
                            <td>${op.userName}</a></td>
                            <td>${op.ordersName}</td>
                            <td>${op.ordersPrice}원</td>
                            <td>${op.ordersQty}개</td>
                            <td>${op.createdAt}</td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="../layout/footer.jsp" %>