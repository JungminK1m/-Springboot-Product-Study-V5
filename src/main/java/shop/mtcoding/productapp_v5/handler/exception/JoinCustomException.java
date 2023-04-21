package shop.mtcoding.productapp_v5.handler.exception;

import org.springframework.http.HttpStatus;

public class JoinCustomException extends RuntimeException {
    // 상태코드
    private HttpStatus status;

    // 생성자
    /*
     * 기존 CustomException은 핸들러에서 alert 창을 띄우기 때문에, alert창을 안 띄우는 새로운 Exception을
     * 생성했다.
     */
    public JoinCustomException(HttpStatus status) {
        this.status = status;
    }
}
