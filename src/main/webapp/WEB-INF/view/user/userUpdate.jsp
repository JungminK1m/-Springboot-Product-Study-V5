<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <form action="/userInfoUpdate" method="post">
                <div class="mb-3 mt-3">
                    유저 이름 :
                    <input id="userName" type="text" value="${user.userName}" readonly="readonly">
                </div>
                <div class="mb-3 mt-3">
                    비밀번호 :
                    <input id="userPassword" type="password" value="${user.userPassword}" placeholder="변경할 비밀번호">
                </div>
                <div class="mb-3 mt-3">
                    이메일 :
                    <input id="userEmail" type="email" value="${user.userEmail}" placeholder="변경할 이메일주소">
                </div>
                <button id="btnUpdateProfile" type="button" class="btn btn-primary">회원수정완료</button>

            </form>
        </div>

        
    <script>

    function updateUserInfo() {

        var updateUserDto = {
            userPassword: $("#userPassword").val(),
            userEmail: $("#userEmail").val()
            };

        $.ajax({
            type : "POST",
            url : "/userInfoUpdate",
            data : JSON.stringify(updateUserDto),
            contentType : "application/json"
        }).done((response) =>{
            if (response.code == 1) {
                alert("회원정보 수정 완료");
                window.location.href = "/userInfo";
            }else{
                alert(response.msg);
            }
        }).fail((err) => {
            console.log(err);
            alert("오류가 발생했습니다.");
        });


    }

    
    </script>

    <%@ include file="../layout/footer.jsp" %>