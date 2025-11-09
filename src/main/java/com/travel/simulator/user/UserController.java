package com.travel.simulator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users") // 이 Controller의 모든 주소는 /api/users로 시작
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입 API
     */
    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto requestDto) {
        // 요청으로 온 JSON 데이터를 DTO 그릇에 담아서 받음
        return userService.signUp(requestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}