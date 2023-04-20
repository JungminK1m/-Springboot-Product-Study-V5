package shop.mtcoding.productapp_v5.dto.user;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.productapp_v5.model.user.User;

@Getter
@Setter

public class UpdateUserDto {
    private String userPassword;
    private String userEmail;

    public User toEntity(Integer userId) {
        User user = new User();
        user.setUserPassword(userPassword);
        user.setUserEmail(userEmail);
        user.setUserId(userId);
        return user;
    }

}
