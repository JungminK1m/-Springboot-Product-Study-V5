package shop.mtcoding.productapp_v5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnumResponseDto<T> {
    private final String msg; // 응답 메시지
    private final T data; // 응답 데이터
}
