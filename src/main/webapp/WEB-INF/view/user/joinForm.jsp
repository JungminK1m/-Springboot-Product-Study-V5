<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <form action="/join" method="post" name="form" onsubmit="return valid()">
        <div class="mb-3 mt-3">
            <input
                    type="text"
                    class="form-control"
                    placeholder="유저이름"
                    id="name"
                    name="userName"
                    required
            />
            <button id="CheckUsername" type="button">중복확인</button>
        </div>
        <div class="mb-3">
            <input
                    type="password"
                    class="form-control"
                    placeholder="비밀번호"
                    id="password"
                    name="userPassword"
                    required
            />
        </div>
        <div class="mb-3">
            <input
                    type="email"
                    class="form-control"
                    placeholder="이메일"
                    id="email"
                    name="userEmail"
                    required
            />
        </div>

        <button
                type="submit"
                class="btn btn-primary">
            회원가입
        </button>
    </form>
</div>

<script>

    // 중복체크 여부 = false - 아직 체크 안했으니까
    let sameCheck = false;

    // 아이디 중복체크
    $('#CheckUsername').on('click', function () {

        // 이렇게 데이터를 변수로 만들면 보기가 편하다
        let data = {userName: $('#name').val()}

        // if (blankUserName() == true) {
        //     alert("이름을 입력해 주세요.");
        //     return; // 조건문을 빠져나가기 위해
        // }

        // if (onlyEng() == false) {
        //     alert("이름은 영어로 입력해주세요")
        //     return;
        // }

        $.ajax({
            url: '/join/checkName',
            type: 'post',
            data: data,
            contentType: "application/x-www-form-urlencoded; charset=utf-8"

        }).done((res) => { // 200
            alert(res.msg);
            // 콘솔창 확인용
            console.log(res);
            console.log("sameCheck : " + sameCheck);
            // 등록 가능하니까 체크 여부를 true로 변경


            sameCheck = true;

        }).fail((err) => { // 200 이 아닌 모든것
            alert(err.responseJSON.msg);
            // 콘솔창 확인용
            console.log(err);
            console.log("sameCheck : " + sameCheck);
            // 등록 불가이기 때문에 중복체크를 안한 것으로 설정 (아래에 이벤트 처리를 위해)
            sameCheck = false;
        });
    });

    // 상품명을 입력하는 input 태그에 값이 변경될 때마다 sameCheck 를 false로 설정하는 이벤트
    // => false가 됐으니 상품명을 다른 걸로 바뀌면 꼭 중복체크를 다시 해야되게 만든다.
    $('#name').on('input', function (e) {
        console.log(e);
        console.log(e.target.value);

        sameCheck = false;
        console.log(sameCheck);
    });

    // 동일 유저 등록하지 못하게 처리하는 이벤트 (최종 회원가입 버튼)
    // form이 submit 될 때 실행되는 이벤트
    function valid(){
        if (blankUserName() == true) {
            alert("아이디를 입력해주세요");
            return false;
        }

        if (blankUserPassword() == true) {
            alert("비밀번호를 222 입력해주세요");
            return false;
        }

        if (emailCheck() == false) {
            alert("이메일 형식을 지켜주세요")
            return false;
        }

        if (onlyEng() == false) {
            alert("이름은 영어로만 입력해주세요")
            return false;
        }

        // == 주의
        if (sameCheck == false) {
            alert("이름 중복체크 해주세요.");
            // e.preventDefault(); = 브라우저가 이벤트를 처리하는 동작을 중단시키는 메서드
            // submit 이벤트를 중단시키기 위해 사용됨
            // e.preventDefault();
            console.log(sameCheck);
            return false;
        } else if (sameCheck == true) {
            alert("회원가입 완료.");
            console.log(sameCheck);
            return true;
        }
    }


    function blankUserName() {	// 아이디 공백 || 띄어쓰기 막아줌
        let username = $("#name").val();
        let blank = /\s/g;
        if (!username || blank.test(username)) {
            return true;
        }
    }

    function blankUserPassword() {	// 비밀번호 공백 || 띄어쓰기 막아줌
        let userpw = $("#password").val();
        let blank = /\s/g;
        if (!userpw || blank.test(userpw)) {
            return true;
        }
    }

    function emailCheck() {	// email 형식
        let email = $("#email").val();
        let emailRule = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
        if (emailRule.test(email)) {
            return true;
        } else {
            return false;
        }
    }

    function onlyEng() {	// 영어만 입력 가능
        let username = $("#name").val();
        let capiRule = /^[a-zA-Z]+$/;
        /* /^[a-zA-Z]+$  <- 영어만! 포함하게 하는 정규식
        * /[a-zA-Z]/ 와 다르다.
        * /[a-zA-Z]/ 정규식은 "Hello, World!"와 같은 문자열에서도 매치되지만, "/^[a-zA-Z]+$/" 정규식은 "HelloWorld"와 같은 문자열에서만 매치됨.
        */
        if (capiRule.test(username)) {
            return true;
        } else {
            return false;
        }
    }
</script>

<%@ include file="../layout/footer.jsp" %>