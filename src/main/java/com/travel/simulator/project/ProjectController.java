package com.travel.simulator.project;

import com.travel.simulator.simulation.dto.ProjectRequestDto; // 👈 1. 안드로이드가 보낸 DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // 👈 2. GetMapping, PostMapping 등 임포트
import com.travel.simulator.simulation.dto.AttractionResponseDto;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects") // 👈 3. 이 Controller의 모든 주소는 /api/projects로 시작
public class ProjectController {

    private final ProjectService projectService; // 👈 4. '일꾼' 주입

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 5. (신규) 프로젝트 저장하기 API
     * (주소: POST /api/projects)
     */
    @PostMapping
    public Project createProject(@RequestBody ProjectRequestDto requestDto) {
        // '일꾼'에게 DTO를 넘겨주고 저장시키기
        return projectService.createProject(requestDto);
    }

    /**
     * 6. (신규) 모든 프로젝트 목록 불러오기 API
     * (주소: GET /api/projects)
     */
    @GetMapping
    public List<Project> getAllProjects() {
        // '일꾼'에게 모든 프로젝트를 가져오라고 시키기
        return projectService.getAllProjects();
    }
    /* @PathVariable: 주소에 포함된 {id} 값을 Long projectId 변수에 넣어줍니다.
     * @RequestBody: 안드로이드가 보낸 '선택된 항공권 JSON'을 Map<String, Object>에 넣어줍니다.
            */
    @PostMapping("/{id}/flight")
    public Project selectFlight(
            @PathVariable("id") Long projectId,
            @RequestBody Map<String, Object> flightData) {

        return projectService.saveSelectedFlight(projectId, flightData);
    }

    /**
     * 3. (신규) 선택한 '관광지' 저장 API
     * (주소: POST /api/projects/{id}/attractions)
     */
    @PostMapping("/{id}/attractions")
    public Project selectAttractions(
            @PathVariable("id") Long projectId,
            // 🚀 타입을 Map<String, Object>로 변경합니다!
            @RequestBody Map<String, Object> attractionsData) {

        // ⚠️ Service 메서드도 Map을 받도록 변경해야 합니다.
        // 이 데이터를 Service로 넘겨 DB에 저장합니다.
        return projectService.saveSelectedAttractions(projectId, attractionsData);
    }

    /**
     * 4. (신규) 선택한 '숙소' 저장 API
     * (주소: POST /api/projects/{id}/hotel)
     */
    @PostMapping("/{id}/hotel")
    public Project selectHotel(
            @PathVariable("id") Long projectId,
            @RequestBody Map<String, Object> hotelData) {

        return projectService.saveSelectedHotel(projectId, hotelData);
    }
}