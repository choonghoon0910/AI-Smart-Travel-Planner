package com.travel.simulator.project;

import com.travel.simulator.simulation.dto.ProjectRequestDto; // 👈 1. 안드로이드가 보낸 DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service // "이 클래스는 실제 로직을 처리하는 '일꾼'입니다"
public class ProjectService {

    private final ProjectRepository projectRepository; // 👈 2. '창고 관리인' 주입

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * 3. (신규) 프로젝트 저장하기 기능
     */
    public Project createProject(ProjectRequestDto requestDto) {
        // 1. 안드로이드가 보낸 DTO를 Project 엔티티로 변환
        Project project = new Project(requestDto);

        // 2. '창고 관리인'에게 시켜서 DB에 저장
        return projectRepository.save(project);
    }
    @Transactional
    public Project saveSelectedFlight(Long projectId, Map<String, Object> flightData) {
        // 1. ID로 프로젝트를 DB에서 찾습니다.
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다: " + projectId));

        // 2. (중요!) 안드로이드가 보낸 항공권 JSON(Map)을
        //    하나의 '문자열'로 변환합니다. (간단한 예시)
        String flightJsonString = flightData.toString(); // (실제로는 Gson/Jackson 라이브러리 사용 권장)

        // 3. 'selectedFlight' 필드를 업데이트하고 DB에 저장(업데이트)합니다.
        project.setSelectedFlight(flightJsonString);
        return projectRepository.save(project);
    }
    @Transactional
    public Project saveSelectedAttractions(Long projectId, Map<String, Object> attractionsData) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다: " + projectId));

        String attractionsJsonString = attractionsData.toString();

        project.setSelectedAttractions(attractionsJsonString);
        return projectRepository.save(project);
    }

    /**
     * 5. (신규) 선택한 '숙소'를 DB에 저장하는 기능
     */
    @Transactional
    public Project saveSelectedHotel(Long projectId, Map<String, Object> hotelData) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다: " + projectId));

        String hotelJsonString = hotelData.toString();

        project.setSelectedHotel(hotelJsonString);
        return projectRepository.save(project);
    }

    /**
     * 4. (신규) 모든 프로젝트 목록 불러오기 기능
     */
    public List<Project> getAllProjects() {
        // '창고 관리인'에게 시켜서 DB의 모든 프로젝트를 반환
        return projectRepository.findAll();
    }
}