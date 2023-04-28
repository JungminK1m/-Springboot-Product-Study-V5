<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container">
            <form action="/product/save" method="post">
                <div class="mb-3 mt-3">
                    상품명 : <input id="name" name="productName" type="text" placeholder="상품명을 적어주세요">
                    <button id="CheckproductName" type="button">중복확인</button>

                </div>

                <div class="mb-3 mt-3">
                    상품가격 : <input id="price" name="productPrice" type="number" placeholder="상품 가격을 적어주세요">
                </div>

                <div class="mb-3 mt-3">
                    상품수량 : <input id="qty" name="productQty" type="number" placeholder="상품 수량을 적어주세요">
                </div>

                <button id="btnSaveProduct" type="submit" class="btn btn-primary">상품등록완료</button>
            </form>
        </div>

        <script>

            // 중복체크 여부 = false - 아직 체크 안했으니까
            let sameCheck = false;

            // 상품명 중복체크
            $('#CheckproductName').on('click', function () {

                // 이렇게 데이터를 변수로 만들면 보기가 편하다
                let data = { productName: $('#name').val() }

                if(blankProductName() == true){
                    alert("상품명을 입력해주세요");
                    return;
                }

                $.ajax({
                    url: '/productSave/checkName/',
                    type: 'post',
                    data: data,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8"

                }).done((res) => {
                    alert("등록 가능한 상품입니다")
                    // 콘솔창 확인용
                    console.log(res);
                    console.log("sameCheck : " + sameCheck);
                    // 등록 가능하니까 체크 여부를 true로 변경
                    sameCheck = true;

                }).fail((err) => {
                    alert("이미 등록한 상품입니다")
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
        
            // 동일 상품명 등록하지 못하게 처리하는 이벤트 (최종 상품 등록 버튼)
            // form이 submit 될 때 실행되는 이벤트
            $('form').on('submit', function(e) {

                if(blankProductName() == true){
                    alert("상품명을 입력해주세요");
                    return;
                }
                
                if(numProductPrice() == false){
                    alert("상품가격에 숫자만 입력해주세요");
                    return;
                }

                if(numProductQty() == false){
                    alert("상품수량에 숫자만 입력해주세요");
                    return;
                }

                // == 주의
                if (sameCheck == false) {
                    alert("상품명 중복확인을 해 주세요.");
                    // e.preventDefault(); = 브라우저가 이벤트를 처리하는 동작을 중단시키는 메서드
                    // submit 이벤트를 중단시키기 위해 사용됨
                    e.preventDefault();
                    console.log(sameCheck);
                }else if (sameCheck == true) {
                    alert("상품이 등록되었습니다.");
                    console.log(sameCheck);
                }

            });

            // 상품등록
            function blankProductName() {	// 상품명 공백 || 띄어쓰기 막아줌
                let productName = $("#name").val();
                let blank = /\s/g;
                if(!productName || blank.test(productName)){
                    return true;
                }
            }

            function numProductPrice() {	// 상품가격에 숫자만 입력 가능
                let productPrice = $("#price").val();
                let numRule = /^[0-9]+$/;
                if (numRule.test(productPrice)) {
                    return true;
                } else {
                    return false;
                }
            }

            function numProductQty() {	// 상품수량에 숫자만 입력 가능
                let productQty = $("#qty").val();
                let numRule = /^[0-9]+$/;
                if (numRule.test(productQty)) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>