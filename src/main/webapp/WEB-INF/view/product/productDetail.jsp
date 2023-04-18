<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="center">
            <div style="margin: 20px;">
                <form type="submit" action="/order/${productId}" method="post">
                    <%-- productName 과 ordersName 연결하기 --%>
                    <input name="ordersId" type="hidden" value="${product.productId}">
                    <input name="ordersName" type="hidden" value="${product.productName}">
                    <input name="ordersPrice" type="hidden" value="${product.productPrice}">
                    <table border="1" style="width: 500px; height: 200px; text-align: center;">

                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <th>${product.productName}</th>
                        </tr>
                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <td>${product.productPrice}원</td>
                        </tr>
                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <td>${product.productQty}개</td>
                        </tr>
                    </table>

                    <c:choose>

                        <%-- 로그인 했을 때만 구매하기 버튼 뜨게 하기 --%>
                        <c:when test="${empty principal}" >
                            <div class="center" style="margin-top: 40px; text-align: center;">
                                <h5>상품을 구매하시려면 로그인 해주세요😀</h5>
                            </div>
                        </c:when>

                        <%-- ADMIN 아닐 때(USER일 때)는 구매하기 버튼 뜨게 하기 --%>
                        <c:when test="${principal.role == 'USER'}">
                            <div class="center" style="margin-top: 20px; text-align: center;">

                                수량 :<input name="ordersQty" type="number" min="0" class="form-control mb-3"
                                    style="width: 200px;">
                                <button
                                    style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">구매하기</button>
                            </div>
                        </c:when>

                        <%-- ADMIN일 때는 수정하기/삭제하기 버튼 뜨게 하기 --%>
                        <c:otherwise>
                            <div class="center" style="margin-top: 20px; text-align: center;">
                                <form type="submit" action="/product/${product.productId}/updateForm" method="get">
                                    <button
                                        style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">수정하기</button>
                                </form>
                                <form type="submit" action="/product/${product.productId}/delete" method="post">
                                    <button
                                        style="width: 240px; height: 50px; margin: auto; background-color: rgb(250, 255, 182);">삭제하기</button>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </form>


            </div>
        </div>
    <%@ include file="../layout/footer.jsp" %>