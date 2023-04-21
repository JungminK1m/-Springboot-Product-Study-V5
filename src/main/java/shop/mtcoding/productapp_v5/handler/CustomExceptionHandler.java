package shop.mtcoding.productapp_v5.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.productapp_v5.handler.exception.CustomException;
import shop.mtcoding.productapp_v5.handler.exception.JoinCustomException;

@RestControllerAdvice
public class CustomExceptionHandler {

    // 무조건 데이터를 응답할 거기 때문에 RestController
    // 자바 스크립트를 응답
    // 어노테이션 ()안에 타입을 적어줘야 함
    // 런타임익셉션 - 모든 익셉션이 다 여기로 올 거임

    // exception터지면 다 여기로 올 거임! 무조건 뒤로가기

    @ExceptionHandler(CustomException.class)
    public String basicException(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    /*
     * 기존 CustomException은 핸들러에서 alert 창을 띄우기 때문에, alert창을 안 띄우는 새로운 Exception을
     * 생성했다.
     * history.back이 있어야 회원가입 시에 예외가 생겨도 기존에 썼던 값이 지워지지 않기 때문에 만듦!
     */
    @ExceptionHandler(JoinCustomException.class)
    public String basicException2(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
