package com.travel.simulator.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor // DTO -> Entity 변환을 위해 기본 생성자 추가
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname; // 👈 디자인에 맞춰 필드 추가!

    private String email;

    private String password; // (실제로는 암호화해서 저장해야 합니다)

    // DTO로부터 객체를 생성하는 생성자
    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}