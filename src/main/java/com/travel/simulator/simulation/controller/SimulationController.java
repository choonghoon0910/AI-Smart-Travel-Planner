package com.travel.simulator.simulation.controller;

// 1. 필요한 DTO들을 모두 임포트합니다.
import com.travel.simulator.simulation.dto.AllSuggestionsDto;
import com.travel.simulator.simulation.dto.AttractionRequestDto;
import com.travel.simulator.simulation.dto.AttractionResponseDto;
import com.travel.simulator.simulation.dto.FlightRequestDto;
import com.travel.simulator.simulation.dto.HotelRequestDto;
import com.travel.simulator.simulation.dto.ProjectRequestDto;
import com.travel.simulator.simulation.service.SimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // 👈 2. 기본 주소를 '/api'로 변경 (더 넓은 범위)
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    /**
     * 3. (신규!) 안드로이드가 최종적으로 호출할 '프로젝트 생성(시뮬레이션)' API
     * (주소: POST /api/projects/create)
     */
    @PostMapping("/projects/create")
    public AllSuggestionsDto createProject(@RequestBody ProjectRequestDto requestDto) {
        // Service의 '최종' 메서드 호출
        return simulationService.getFinalSuggestions(requestDto);
    }


    // --- 4. (기존) 테스트용 API들 ---
    // (기본 주소가 /api로 바뀌었으므로, /simulate를 경로에 추가)

    /**
     * (테스트용) 항공권 시뮬레이션 API
     * (주소: POST /api/simulate/flights)
     */
    @PostMapping("/simulate/flights")
    public String getFlightSimulation(@RequestBody FlightRequestDto requestDto) {
        return simulationService.getFlightSimulation(requestDto);
    }

    /**
     * (테스트용) '관광지' AI 연동 테스트용 API
     * (주소: POST /api/simulate/tourist)
     */
    @PostMapping("/simulate/tourist")
    public List<AttractionResponseDto> simulateAttractions(@RequestBody AttractionRequestDto requestDto) {
        return simulationService.getAttractionsFromAi_FOR_TEST(requestDto);
    }

    /**
     * (테스트용) '호텔' AI 연동 테스트용 API
     * (주소: POST /api/simulate/hotel)
     */
    @PostMapping("/simulate/hotel")
    public String simulateHotel(@RequestBody HotelRequestDto requestDto) {
        return simulationService.getHotelSimulation_FOR_TEST(requestDto);
    }
}