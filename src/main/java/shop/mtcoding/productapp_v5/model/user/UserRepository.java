package shop.mtcoding.productapp_v5.model.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.productapp_v5.dto.user.AdminLoginDto;
import shop.mtcoding.productapp_v5.dto.user.JoinDto;
import shop.mtcoding.productapp_v5.dto.user.LoginDto;

@Mapper
public interface UserRepository {

    public void insert(JoinDto joinDto);

    public User findById(Integer userId);

    public User findByUserName(String userName);

    public User findByUserEmail(String userEmail);

    public User findByUsernameAndPassword(LoginDto loginDto);

    public List<User> findAll();

    public void update(User user);

    public void delete(Integer userId);

    public User login(LoginDto loginDto);

    public User adminLogin(AdminLoginDto adminLoginDto);
}
