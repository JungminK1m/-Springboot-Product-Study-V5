<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <form action="/admin/login" method="post" name="form">
            
                <div class="mb-3 mt-3">
                    <input type="text" class="form-control" placeholder="username" name="userName" value="admin" />
                </div>

                <div class="mb-3">
                    <input type="password" class="form-control" placeholder="password" name="userPassword" value="1234" />
                </div>

                <button type="submit" class="btn btn-primary">
                    관리자 로그인
                </button>

            </form>
            
         </div>

        <%@ include file="../layout/footer.jsp" %>