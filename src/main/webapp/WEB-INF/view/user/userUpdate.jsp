<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <form>
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
    // 쓸 때 input name 속성 id로 바꾸기!!!!!!!

    $("#btnUpdateProfile").click(()=>{
    userUpdate();
    });


    function userUpdate(){
        let data ={
            userPassword : $("#userPassword").val(),
            userEmail : $("#userEmail").val()
        };
        
        $.ajax("/userInfoUpdate", {
            type : "POST",
            dataType : "json",
            data : JSON.stringify(data),
            headers :{ //http header에 들고 갈 요청 데이터
                "Content-Type" : "application/json"	//json 타입의 컨텍트 타입을 날림
            }
        }).done((res)=> {
            if(res.code == 1){
                alert("회원정보 수정 완료");
                location.href = "/userInfo";
            }
        }).fail((res)=>{
            alert("회원정보 수정 실패");
        });
    }
    </script>

    <%@ include file="../layout/footer.jsp" %>