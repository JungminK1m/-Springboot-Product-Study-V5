package shop.mtcoding.productapp_v5.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
    USER_UPDATE_SAME_EMAIL("이미 사용중인 email 입니다.", HttpStatus.BAD_REQUEST),

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
