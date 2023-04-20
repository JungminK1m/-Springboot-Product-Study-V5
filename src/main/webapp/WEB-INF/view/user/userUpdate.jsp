<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <form action="/userInfoUpdate" method="post">
                <div class="mb-3 mt-3">
                    유저 이름 :
                    <input name="userName" type="text" value="${user.userName}" readonly="readonly">
                </div>
                <div class="mb-3 mt-3">
                    비밀번호 :
                    <input name="userPassword" type="password" value="${user.userPassword}" placeholder="변경할 비밀번호">
                </div>
                <div class="mb-3 mt-3">
                    이메일 :
                    <input name="userEmail" type="email" value="${user.userEmail}" placeholder="변경할 이메일주소">
                </div>
                <button type="submit" class="btn btn-primary">회원수정완료</button>

            </form>
        </div>

    <%@ include file="../layout/footer.jsp" %>