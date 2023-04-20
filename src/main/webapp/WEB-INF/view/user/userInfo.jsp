<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

    <div class="center">
        <div style="margin: 20px;">

            <form>
                    
                <table border="1" style="width: 500px; height: 200px; text-align: center;">

                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">이름</th>
                        <th>${user.userName}</th>
                    </tr>
                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">비밀번호</th>
                        <td>${user.userPassword}</td>
                    </tr>
                    <tr style="border: 1px solid">
                        <th style="background-color: rgb(185, 185, 185)">이메일</th>
                        <td>${user.userEmail}</td>
                    </tr>
                </table>
            </form>

                <div class="center" style="margin-top: 40px; text-align: center;">

                    <form action="/userUpdateForm" method="get">
                        <button
                            style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">수정하기</button>
                    </form>
                    <form action="/deleteUser" method="post">
                        <button
                            style="width: 240px; height: 50px; margin: auto; background-color: rgb(250, 255, 182);">탈퇴하기</button>
                    </form>

                </div>


        </div>
    </div>

      
    <%@ include file="../layout/footer.jsp" %>