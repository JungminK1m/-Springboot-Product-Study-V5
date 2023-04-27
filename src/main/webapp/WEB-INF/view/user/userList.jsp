<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="text-center m-4">
            <h1>유저목록 페이지</h1>
        </div>
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>유저이름</th>
                        <th>비밀번호</th>
                        <th>이메일</th>
                        <th>가입형태</th>
                        <th>가입일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${user}" var="user" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${user.userName}</a></td>
                            <td>${user.userPassword}</td>
                            <td>${user.userEmail}</td>
                            <td>${user.role}</td>
                            <td>${user.createdAt}</td>
                            <td>
                                <form action="/deleteUser/${user.userId}" method="post">
                                    <button class="btn btn-secondary btn-sm" type="submit">회원삭제</button>
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="../layout/footer.jsp" %>