<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
    
    <div class="container">
        <form action="/join" method="post" name="form">
                <div class="mb-3 mt-3">
                    <input
                        type="text"
                        class="form-control"
                        placeholder="아이디"
                        id="name"
                        name="userName"
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
                    />
                </div>
                <div class="mb-3">
                    <input
                        type="email"
                        class="form-control"
                        placeholder="이메일"
                        id="email"
                        name="userEmail"
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

            // 상품명 중복체크
            $('#CheckUsername').on('click', function () {

                // 이렇게 데이터를 변수로 만들면 보기가 편하다
                let data = { userName: $('#name').val() }

                $.ajax({
                    url: '/join/checkName/',
                    type: 'post',
                    data: data,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8"

                }).done((res) => {
                    alert("등록 가능한 유저입니다")
                    // 콘솔창 확인용
                    console.log(res);
                    console.log("sameCheck : " + sameCheck);
                    // 등록 가능하니까 체크 여부를 true로 변경
                    sameCheck = true;

                }).fail((err) => {
                    alert("이미 등록된 유저입니다")
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
                sameCheck = false;
                console.log(sameCheck);
            });
        
            // 동일 유저 등록하지 못하게 처리하는 이벤트 (최종 회원가입 버튼)
            // form이 submit 될 때 실행되는 이벤트
            $('form').on('submit', function(e) {
                // == 주의
                if (sameCheck == false) {
                    alert("이름 중복체크 해주세요.");
                    // e.preventDefault(); = 브라우저가 이벤트를 처리하는 동작을 중단시키는 메서드
                    // submit 이벤트를 중단시키기 위해 사용됨
                    e.preventDefault();
                    console.log(sameCheck);
                }else if (sameCheck == true) {
                    alert("회원가입 완료.");
                    console.log(sameCheck);
                }
            });
        </script>

    <%@ include file="../layout/footer.jsp" %>