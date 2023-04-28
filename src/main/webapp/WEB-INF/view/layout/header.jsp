<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
        <title>Product</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        </head>
        <style>
            .gitlink {
                text-decoration-line: none;
                color: rgb(255, 105, 138);
                font-weight: bolder;
                background-color: rgb(255, 228, 154);
            }

            .center {
                display: flex;
                justify-content: center;
            }
        </style>

        <body>
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <div class="container-fluid">
                    <h3 style="color: white;">🍌 쇼핑몰 🍓</h3>

                    <div class="collapse navbar-collapse" id="collapsibleNavbar">
                        <ul class="navbar-nav">

                            <%-- 공통 헤더 --%>
                                <li class="nav-item">
                                    <a class="nav-link" href="/product">상품목록페이지</a>
                                </li>

                                <c:choose>

                                    <%-- 로그인 하지 않았을 때 헤더 --%>
                                        <c:when test="${empty principal}">
                                            <li class="nav-item">
                                                <a class="nav-link" href="/loginForm">구매자 로그인</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="/admin/loginForm">관리자 로그인</a>
                                            </li>
                                        </c:when>

                                        <%-- USER로 로그인 했을 때 헤더 --%>
                                            <c:when test="${principal.role == 'USER'}">
                                                <li class="nav-item">
                                                    <a class="nav-link" href="/ordersList/${principal.userId}">주문조회</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="/userInfo">마이페이지</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="/logout">로그아웃</a>
                                                </li>
                                            </c:when>


                                            <%-- ${principal.role=='ADMIN' } 일 때 헤더 --%>
                                                <c:otherwise>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="/admin/productSave">상품등록페이지</a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="/admin/userList">유저관리</a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="/admin/userOrdersList">유저구매목록</a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="/userInfo">마이페이지</a>
                                                    </li>
                                                    <li class="nav-item">
                                                        <a class="nav-link" href="/logout">로그아웃</a>
                                                    </li>
                                                </c:otherwise>

                                </c:choose>
                        </ul>
                    </div>
                </div>
            </nav>
        </body>

        </html>