package shop.mtcoding.productapp_v5.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginDto {
    private String userName;
    private String userPassword;
    private String role;
}
