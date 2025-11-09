package com.travel.simulator.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository를 상속받으면 save(), findById() 등이 자동 생성됩니다.

    Optional<User> findByEmail(String email);
}