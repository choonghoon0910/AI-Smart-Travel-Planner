package com.travel.simulator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입 로직
     */
    public String signUp(SignUpRequestDto requestDto) {
        // (나중에 여기에 '이메일 중복 확인' 로직을 추가해야 합니다)

        // DTO를 Entity로 변환
        User user = new User(
                requestDto.getNickname(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        // Repository를 통해 DB에 저장
        userRepository.save(user);

        return "회원가입 성공";
    }

    public String login(LoginRequestDto requestDto) {
        // 1. 이메일로 사용자를 찾는다.
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이메일입니다."));

        // 2. (⚠️ 절대 따라하면 안되는 방식!) 비밀번호가 일치하는지 확인한다.
        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. (임시) 로그인 성공 메시지를 반환한다.
        // (나중에는 여기서 JWT 같은 토큰을 생성해서 반환해야 합니다)
        return user.getNickname() + "님, 로그인 성공!";
    }
}