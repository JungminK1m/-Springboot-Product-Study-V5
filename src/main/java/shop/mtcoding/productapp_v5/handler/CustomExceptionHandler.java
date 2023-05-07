package shop.mtcoding.productapp_v5.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.productapp_v5.dto.EnumResponseDto;
import shop.mtcoding.productapp_v5.enums.ResponseEnum;
import shop.mtcoding.productapp_v5.handler.exception.CustomApiException;
import shop.mtcoding.productapp_v5.handler.exception.CustomException;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 무조건 데이터를 응답할 거기 때문에 RestController
    // 자바 스크립트를 응답
    // 어노테이션 ()안에 타입을 적어줘야 함
    // 런타임익셉션 - 모든 익셉션이 다 여기로 올 거임

    // exception터지면 다 여기로 올 거임! 무조건 뒤로가기
    @ExceptionHandler(CustomException.class)
    public String basicException(CustomException e) {
        ResponseEnum responseEnum = e.getResponseEnum();
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + responseEnum.getMsg() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<EnumResponseDto<?>> customExceptionHandler(CustomApiException e) {
        ResponseEnum responseEnum = e.getResponseEnum();
        return ResponseEntity.status(responseEnum.getStatus())
                .body(new EnumResponseDto<>(responseEnum.getMsg(), null));
    }

}
