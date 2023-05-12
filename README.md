# 쇼핑몰 프로젝트 5단계 📦
# 시연 영상 🎥
##### 사진을 누르면 유튜브로 이동합니다
[![5조 시연영상](https://img.youtube.com/vi/aEpCiDY8tpI/0.jpg)](https://www.youtube.com/watch?v=aEpCiDY8tpI)
<br/><br/>


# PPT 📊
[5조 파이널 프로젝트.pdf](https://github.com/JungminK1m/Springboot-Product-Study-V5/files/11458707/5.pdf)
<br/><br/>


# 화면 구현 📺

### 관리자(판매자)👷‍♀️👷‍♂️
- 상품 등록, 수정, 삭제하기
- 상품 목록보기
  - 상품 상세보기
- 유저 관리하기
  - 유저 목록 보기
  - 유저 삭제하기
- 유저 구매목록 보기
- 마이페이지
  - 회원 정보 수정
  - 탈퇴하기

  <br/>
### 구매자🙍‍♂️🙍‍♀️
- 상품 목록보기
  - 상품 상세보기
- 주문 조회 페이지
  - 주문 취소하기
- 마이페이지
  - 회원 정보 수정
  - 탈퇴하기
  
  <br/>
# 사용기술 🧪
![Springboot](https://img.shields.io/badge/-Springboot-6DB33F)
![Java](https://img.shields.io/badge/-Java-F09820)
![CSS](https://img.shields.io/badge/-CSS-1572B6)
![HTML](https://img.shields.io/badge/-HTML-E34F26)
![JS](https://img.shields.io/badge/-JavaScript-F7DF1E)
![Bootstrap](https://img.shields.io/badge/-Bootstrap-7952B3)
![MyBatis](https://img.shields.io/badge/-MyBatis-B10000)
![H2](https://img.shields.io/badge/-H2Console-41BDF5)  
  <br/>
# 기능구현 🔧
### 공통👨‍👩‍👧‍👦
- user 테이블에 role(권한) 추가하여 권한에 따른 화면 분리
  - 비로그인, 'USER' 로그인, 'ADMIN' 로그인 3가지 경우로 분리
    
  <br/>
- 로그인 유효성 체크
- 로그인 **인터셉터** 적용
- 회원 정보 수정, 회원 탈퇴 AJAX 처리
- **CustomException을 ENUM으로 처리** ⏬
```java
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    PRINCIPAL_DOSE_NOT_EXIST("로그인을 먼저 해 주세요.", HttpStatus.BAD_REQUEST),

    ADMIN_LOGIN_FAIL("관리자 로그인을 먼저 해 주세요.", HttpStatus.FORBIDDEN),
    ADMIN_DELETE_USER_FAIL("유저 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST),
    ADMIN_CHECK_ROLE("접근 권한이 없습니다.", HttpStatus.FORBIDDEN),

    USER_USERNAME_EMPTY("유저 이름을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    USER_PASSWORD_EMPTY("비밀번호를 입력해 주세요.", HttpStatus.BAD_REQUEST),
    USER_EMAIL_EMPTY("이메일을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    USER_LOGIN_INFO_DOSE_NOT_MATCH("유저 이름과 비밀번호를 확인해 주세요.", HttpStatus.BAD_REQUEST),

    USER_JOIN_SAME_USERNAME("이미 가입된 username 입니다.", HttpStatus.BAD_REQUEST),
    USER_JOIN_SAME_EMAIL("이미 가입된 email 입니다.", HttpStatus.BAD_REQUEST),

    PRODUCT_NAME_ALREADY_SAVED("이미 등록한 상품입니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_QTY_NO_MORE_THAN_ZERO("상품 갯수가 0개 이하일 수 없습니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_SAVE_FAIL("상품 등록을 실패했습니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_UPDATE_FAIL("상품 업데이트를 실패했습니다.", HttpStatus.BAD_REQUEST),
    PRODUCT_DELETE_FAIL("상품 삭제를 실패했습니다.", HttpStatus.BAD_REQUEST),

    PRODUCT_NAME_EMPTY("상품 이름을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_EMPTY("상품 가격을 입력해 주세요.", HttpStatus.BAD_REQUEST),
    PRODUCT_QTY_EMPTY("상품 재고를 입력해 주세요.", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_QTY_ZERO("상품 가격과 재고를 입력해 주세요.", HttpStatus.BAD_REQUEST),

    NO_ACCESS_TO_ORDERSLIST("구매목록을 볼 권한이 없습니다.", HttpStatus.FORBIDDEN),
    NO_MORE_THAN_PRODUCT_QTY("재고보다 더 많은 수량을 구매할 수 없습니다.", HttpStatus.BAD_REQUEST);

    private final String msg;
    private final HttpStatus status;
}
```
##### ENUM 적용 전&후 
![image](https://github.com/JungminK1m/Springboot-Product-Study-V5/assets/118741874/02b30367-2296-4ace-98db-c40fbcddbaee)

---  
   <br/>

### 관리자(판매자)👷‍♀️👷‍♂️
#### ✔ 상품등록
- 상품명, 가격, 재고 유효성 검사
- 상품명 중복체크
#### ✔ 유저관리
- 유저 강제 삭제 (삭제된 유저 로그인 불가)

   <br/>
---
<br/>

### 구매자🙍‍♂️🙍‍♀️
#### ✔ 회원가입 
- 아이디, 비밀번호 공백 막기
- 이메일 형식 지키기
- 아이디 영어만 허용하기
- 아이디 중복체크
- 사용 중인 아이디, 이메일로 가입불가

#### ✔ 상품구매
- 상품 구매 시 재고 업데이트
- 상품 구매 수량 자바스크립트로 유효성 검사
#### ✔ 주문조회
- 주문 취소 시 재고 업데이트

   <br/>
   
# ERD 다이어그램 📊
![쇼핑몰ERD](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/6b422b44-0d43-4b73-9529-3533832b8c96)

# 테이블 생성 📁
```sql
CREATE TABLE user_tb(
	user_id INT PRIMARY KEY auto_increment,
	user_name VARCHAR(20) NOT null,
	user_password VARCHAR(20) NOT null,
	user_email VARCHAR(20) NOT null,
	role VARCHAR(20) NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE product_tb(
	product_id INT PRIMARY KEY auto_increment,
	product_name VARCHAR(20) NOT null,
	product_price INT NOT null,
	product_qty INT NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE orders_tb(
    orders_id INT PRIMARY KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price INT NOT null,
    orders_qty INT NOT null,
    product_id INT NOT null,
    user_id INT NOT null,
    created_at TIMESTAMP
);
```
# 더미 데이터 📰
```sql
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('ssar', '1234', 'ssar@nate.com','USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('cos', '1234', 'cos@nate.com', 'USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('admin', '1234', 'admin@nate.com', 'ADMIN', NOW());

INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('바나나', 3000, 98, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('딸기', 2000, 100, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('키위', 4000, 85, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('오렌지', 3500, 50, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('사과', 1000, 200, NOW());

INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at) VALUES ('바나나', 3000, 2, 1, 1, NOW());
INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('딸기', 2000, 5, 2, 2, NOW());
```

