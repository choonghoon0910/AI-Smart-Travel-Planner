package com.travel.simulator.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    // UI에서 받을 3가지 정보
    private String nickname;
    private String email;
    private String password;
}