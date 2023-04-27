package shop.mtcoding.productapp_v5.model.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Timestamp createdAt;
    private String role;

    public User(String userName, String userPassword, String userEmail, String role) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.role = role;
        // 거르기 위해서 사용!! -> JoinDto > toEntity에서 적어준 값으로 들어감
        // 예시 ) this.role = "USER"; // 무조건 USER로 값이 들어감
    }

    // userUpdateDto.toEntity의 생성자
    // public User(String userPassword, String userEmail, Integer userId) {
    // this.userPassword = userPassword;
    // this.userEmail = userEmail;
    // this.userId = userId;
    // }

}
