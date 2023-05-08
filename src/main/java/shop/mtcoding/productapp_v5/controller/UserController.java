package shop.mtcoding.productapp_v5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.productapp_v5.dto.ResponseDto;
import shop.mtcoding.productapp_v5.dto.user.AdminLoginDto;
import shop.mtcoding.productapp_v5.dto.user.JoinDto;
import shop.mtcoding.productapp_v5.dto.user.LoginDto;
import shop.mtcoding.productapp_v5.dto.user.UpdateUserDto;
import shop.mtcoding.productapp_v5.enums.ResponseEnum;
import shop.mtcoding.productapp_v5.handler.exception.CustomApiException;
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
            throw new CustomException(ResponseEnum.USER_USERNAME_EMPTY);
        }
        if (loginDto.getUserPassword().isEmpty()) {
            throw new CustomException(ResponseEnum.USER_PASSWORD_EMPTY);
        }

        // 가입된 유저인지 확인
        User userPS = userRepository.login(loginDto);
        if (userPS == null || !userPS.getRole().equals("USER")) {
            // 로그인 실패
            throw new CustomException(ResponseEnum.USER_LOGIN_INFO_DOSE_NOT_MATCH);
        }

        session.setAttribute("principal", userPS);

        // 로그인 성공
        return "redirect:/product";
    }

    // 관리자 로그인
    @PostMapping("/admin/login")
    public String adminLogin(AdminLoginDto adminLoginDto) {

        // 유효성 체크
        if (adminLoginDto.getUserName().isEmpty()) {
            throw new CustomApiException(ResponseEnum.USER_USERNAME_EMPTY);
        }

        if (adminLoginDto.getUserPassword().isEmpty()) {
            throw new CustomApiException(ResponseEnum.USER_PASSWORD_EMPTY);
        }

        // null 오류!!!! 수정해야 함!
        // System.out.println("관리자 로그인 패스워드 통과");
        // if (!adminLoginDto.getRole().equals("ADMIN")) {
        // throw new CustomException(ResponseEnum.ADMIN_CHECK_ROLE);
        // }
        // System.out.println("관리자 로그인 권한 체크 완료");

        User userPS = userRepository.adminLogin(adminLoginDto);

        if (userPS == null) {

            // 로그인 실패
            throw new CustomApiException(ResponseEnum.USER_LOGIN_INFO_DOSE_NOT_MATCH);

        }

        // 로그인 성공
        session.setAttribute("principal", userPS);

        return "redirect:/product";

    }

    @PostMapping("/join")
    public String join(JoinDto joinDto) {

        // 유효성 체크
        if (joinDto.getUserName().isEmpty()) {
            // System.out.println("JoinCustomException - userName 실행됨");
            throw new CustomException(ResponseEnum.USER_USERNAME_EMPTY);
        }
        if (joinDto.getUserPassword().isEmpty()) {
            // System.out.println("JoinCustomException - userPassword 실행됨");
                throw new CustomException(ResponseEnum.USER_PASSWORD_EMPTY);
        }
        if (joinDto.getUserEmail().isEmpty()) {
            // System.out.println("JoinCustomException - userEmail 실행됨");
            throw new CustomException(ResponseEnum.USER_EMAIL_EMPTY);
        }

        // 기존 동일 유저 확인 (username,email만)
        if (userRepository.findByUserName(joinDto.getUserName()) != null) {
            throw new CustomException(ResponseEnum.USER_JOIN_SAME_USERNAME);
        }
        if (userRepository.findByUserEmail(joinDto.getUserEmail()) != null) {
            throw new CustomException(ResponseEnum.USER_JOIN_SAME_EMAIL);
        }

        userRepository.insert(joinDto);

        return "redirect:/loginForm";
    }

    // 유저네임 중복체크 컨트롤러
    @PostMapping("/join/checkName")
    public ResponseEntity<?> CheckUsername(@RequestParam String userName) {

        // 디버깅
        System.out.println("userName : [" + userName+"]");

        if(userName == null || userName.length() == 0){
            System.out.println("여기 걸림?");
            throw new CustomApiException(ResponseEnum.USER_USERNAME_EMPTY);
        }
        System.out.println("여기도 실행됨?");
        // DB에 중복이 된 값이 있는 지 확인
        User un = userRepository.findByUserName(userName);

        if (un != null) {
            throw new CustomApiException(ResponseEnum.USER_JOIN_SAME_USERNAME);
        }
        // pn == null 기존에 없던 유저이기 때문에 true 반환
        ResponseDto<?> responseDto = new ResponseDto<>(1, "중복체크완료", null);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 관리자 - 유저 삭제
    @PostMapping("/admin/deleteUser/{userId}")
    public String deleteUser(@PathVariable Integer userId) {

        // 관리자 로그인 한 사람만 접근 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomApiException(ResponseEnum.ADMIN_LOGIN_FAIL);
        }

        int result = userRepository.delete(userId);
        if (result != 1) {
            throw new CustomApiException(ResponseEnum.ADMIN_DELETE_USER_FAIL);
        }
        return "redirect:/admin/userList";
    }

    // 유저 정보 수정 AJAX
    @PostMapping("/userInfoUpdate")
    public @ResponseBody ResponseDto<?> userUpdate(@RequestBody UpdateUserDto updateUserDto) {
        User principal = (User) session.getAttribute("principal");

        // 수정 시 이메일 유효성 검사 (다른 유저와 중복 방지)
        User checkUserEmail = userRepository.findByUserEmail(updateUserDto.getUserEmail());
        if (checkUserEmail != null && !checkUserEmail.getUserId().equals(principal.getUserId())) {
            throw new CustomApiException(ResponseEnum.USER_UPDATE_SAME_EMAIL);
        }
        userRepository.update(updateUserDto.toEntity(principal.getUserId()));
        return new ResponseDto<>(1, "회원정보수정성공", null);
    }

    // 회원 탈퇴 AJAX
    @PostMapping("/deleteUser")
    public @ResponseBody ResponseDto<?> userDelete() {
        User principal = (User) session.getAttribute("principal");
        int result = userRepository.delete(principal.getUserId());
        session.invalidate();
        return new ResponseDto<>(1, "회원탈퇴성공", null);
    }

    // 로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    // 회원가입 페이지
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // 관리자 - 로그인 페이지
    @GetMapping("/admin/loginForm")
    public String adminLoginForm() {
        return "user/adminLoginForm";
    }

    @GetMapping("/logout")
    public String logout() {

        session.invalidate();
        return "redirect:/";
    }

    // 관리자 - 유저 목록 페이지
    @GetMapping("/admin/userList")
    public String userList(Model model) {

        // 관리자 로그인 한 사람만 접근 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null || !principal.getRole().equals("ADMIN")) {
            throw new CustomException(ResponseEnum.ADMIN_LOGIN_FAIL);
        }

        List<User> userList = userRepository.findAll();
        model.addAttribute("user", userList);

        return "user/userList";
    }

    // 관리자,유저 - 회원정보 페이지(마이페이지)
    @GetMapping("/userInfo")
    public String userInfo(Model model) {

        // 세션에 저장된 값을 들고오면 주소값에 @PathVariable을 쓰지 않아도 됨
        // 세션에 있는 사람 (=로그인 한 사람)만 접근 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException(ResponseEnum.PRINCIPAL_DOSE_NOT_EXIST);
        }

        // 세션에 저장된 Id 값으로 본인 정보 불러와서 모델에 담기
        User userPS = userRepository.findById(principal.getUserId());
        model.addAttribute("user", userPS);

        return "user/userInfo";
    }

    @GetMapping("/userUpdateForm")
    public String userUpdateForm(Model model) {

        // 세션에 저장된 값을 들고오면 주소값에 @PathVariable을 쓰지 않아도 됨
        // 세션에 있는 사람 (=로그인 한 사람)만 접근 가능
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            throw new CustomException(ResponseEnum.PRINCIPAL_DOSE_NOT_EXIST);
        }

        // 세션에 저장된 Id 값으로 본인 정보 불러와서 모델에 담기
        User userPS = userRepository.findById(principal.getUserId());
        model.addAttribute("user", userPS);

        return "user/userUpdate";
    }

}
