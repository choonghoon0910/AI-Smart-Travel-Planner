package com.travel.simulator.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // JpaRepository를 상속받는 것만으로
    // save(), findAll(), findById() 등이 자동 생성됩니다!
}