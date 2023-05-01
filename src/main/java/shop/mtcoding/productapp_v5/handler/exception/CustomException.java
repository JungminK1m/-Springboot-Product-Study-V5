package shop.mtcoding.productapp_v5.handler.exception;

import org.springframework.http.HttpStatus;

import shop.mtcoding.productapp_v5.enums.ResponseEnum;

public class CustomException extends RuntimeException {

    private final ResponseEnum responseEnum;

    public CustomException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

}
