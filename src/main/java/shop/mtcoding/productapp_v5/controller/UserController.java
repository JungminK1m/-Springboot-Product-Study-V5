package shop.mtcoding.productapp_v5.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.productapp_v5.dto.user.AdminLoginDto;
import shop.mtcoding.productapp_v5.dto.user.JoinDto;
import shop.mtcoding.productapp_v5.dto.user.LoginDto;
import shop.mtcoding.productapp_v5.handler.exception.CustomException;
import shop.mtcoding.productapp_v5.model.user.User;
import shop.mtcoding.productapp_v5.model.user.UserRepository;

@Controller
public class UserController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    // 구매자 로그인
    @PostMapping("/login")
    public String login(LoginDto loginDto) {

        // 유효성 체크
        if (loginDto.getUserName().isEmpty()) {
            throw new CustomException("username을 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }
        if (loginDto.getUserPassword().isEmpty()) {
            throw new CustomException("password를 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }

        // 가입된 유저인지 확인
        User userPS = userRepository.login(loginDto);
        if (userPS != null && userPS.getRole().equals("USER")) {
            session.setAttribute("principal", userPS);

            System.out.println("userName : " + userPS.getUserName());
            System.out.println("userPassword : " + userPS.getUserPassword());
            System.out.println("Role : " + userPS.getRole());
            System.out.println("user 로그인 성공");

            // 로그인 성공
            return "redirect:/product";

        }
        // 로그인 실패
        throw new CustomException("아이디와 비밀번호를 확인해 주세요", HttpStatus.BAD_REQUEST);
    }

    // 관리자 로그인
    @PostMapping("/adminLogin")
    public String adminLogin(AdminLoginDto adminLoginDto) {

        // 유효성 체크
        if (adminLoginDto.getUserName().isEmpty()) {
            throw new CustomException("username을 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }
        if (adminLoginDto.getUserPassword().isEmpty()) {
            throw new CustomException("password를 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }

        User userPS = userRepository.adminLogin(adminLoginDto);

        if (userPS != null && userPS.getRole().equals("ADMIN")) {

            session.setAttribute("principal", userPS);

            System.out.println("adminName : " + userPS.getUserName());
            System.out.println("adminPassword : " + userPS.getUserPassword());
            System.out.println("Role : " + userPS.getRole());
            System.out.println("admin 로그인 성공");

            // 로그인 성공
            return "redirect:/product";
        }
        // 로그인 실패
        return "redirect:/adminLoginForm";

    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {

        // 유효성 체크
        if (joinDto.getUserName().isEmpty()) {
            throw new CustomException("username을 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }
        if (joinDto.getUserPassword().isEmpty()) {
            throw new CustomException("password를 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }
        if (joinDto.getUserEmail().isEmpty()) {
            throw new CustomException("email을 입력해 주세요.", HttpStatus.BAD_REQUEST);
        }

        // 기존 동일 유저 확인 (username,email만)
        if (userRepository.findByUserName(joinDto.getUserName()) != null) {
            throw new CustomException("이미 가입된 유저입니다.", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByUserEmail(joinDto.getUserEmail()) != null) {
            throw new CustomException("이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        userRepository.insert(joinDto);

        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/adminLoginForm")
    public String adminLoginForm() {
        return "user/adminLoginForm";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

}
