package com.travel.simulator.simulation.service;

// 1. 필요한 모든 DTO와 유틸리티를 임포트합니다.
import com.travel.simulator.simulation.dto.AllSuggestionsDto;
import com.travel.simulator.simulation.dto.AttractionRequestDto;
import com.travel.simulator.simulation.dto.AttractionResponseDto;
import com.travel.simulator.simulation.dto.FlightRequestDto;
import com.travel.simulator.simulation.dto.FlightResponseDto;
import com.travel.simulator.simulation.dto.HotelRequestDto;
import com.travel.simulator.simulation.dto.HotelResponseDto;
import com.travel.simulator.simulation.dto.ProjectRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service // "이 클래스는 실제 로직을 처리하는 '일꾼'입니다."
public class SimulationService {

    private final RestTemplate restTemplate;

    // --- AI 서버 주소들 (사용자님이 입력한 주소) ---
    private final String aiFlightServerUrl = "http://192.168.0.21:8080/api/simulate/flights";
    private final String aiAttractionServerUrl = "http://192.168.0.21:8080/api/simulate/tourist";
    private final String aiHotelServerUrl = "http://192.168.0.21:8080/api/simulate/hotel";

    @Autowired
    public SimulationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 2. (신규!) 안드로이드가 호출할 '최종 시뮬레이션' 메서드
     * (이 메서드는 SimulationController의 'createProject'가 호출합니다)
     */
    public AllSuggestionsDto getFinalSuggestions(ProjectRequestDto allData) {

        // --- 1. 항공권 DTO로 변환 및 AI 호출 ---
        // (AI 항공권 Input DTO 형식에 맞게 allData에서 데이터를 꺼내 씁니다)
        FlightRequestDto flightRequest = new FlightRequestDto(
                allData.getDepartureAirport(),
                allData.getCity(), // (AI 명세에 따라 city 또는 country)
                allData.getStartDate(),
                allData.getEndDate(),
                allData.getAdults(),
                allData.getChildren(),
                allData.getInfants(),
                allData.getCabinClass(),
                "KRW",
                5,          // (top_n)
                false       // (use_api)
        );
        // (가정: 항공권 AI는 FlightResponseDto[]를 반환)
        FlightResponseDto[] flightArray = restTemplate.postForObject(
                aiFlightServerUrl, flightRequest, FlightResponseDto[].class
        );
        List<FlightResponseDto> flights = (flightArray != null) ? Arrays.asList(flightArray) : List.of();


        // --- 2. 관광지 DTO로 변환 및 AI 호출 ---
        AttractionRequestDto attractionRequest = new AttractionRequestDto(
                allData.getCountry(),
                allData.getCity(),
                allData.getTheme(),
                15,      // (count)
                false    // (use_api)
        );
        AttractionResponseDto[] attractionArray = restTemplate.postForObject(
                aiAttractionServerUrl, attractionRequest, AttractionResponseDto[].class
        );
        List<AttractionResponseDto> attractions = (attractionArray != null) ? Arrays.asList(attractionArray) : List.of();


        // --- 3. 숙소 DTO로 변환 및 AI 호출 ---
        // (가정: 숙소 AI는 날짜와 관광지 목록(방금 받은)이 필요)
        HotelRequestDto hotelRequest = new HotelRequestDto(
                allData.getStartDate(),
                allData.getEndDate(),
                attractions // (방금 AI에게 받은 관광지 리스트를 다시 Input으로 사용)
        );
        // (가정: 호텔 AI는 HotelResponseDto[]를 반환)
        HotelResponseDto[] hotelArray = restTemplate.postForObject(
                aiHotelServerUrl, hotelRequest, HotelResponseDto[].class
        );
        List<HotelResponseDto> hotels = (hotelArray != null) ? Arrays.asList(hotelArray) : List.of();


        // --- 4. 3개의 리스트를 하나의 DTO로 묶어서 Controller에 반환 ---
        return new AllSuggestionsDto(flights, attractions, hotels);
    }


    // --- 3. (기존) 테스트용 메서드들 ---

    /**
     * (테스트용) 항공권 시뮬레이션을 요청하는 메서드
     */
    public String getFlightSimulation(FlightRequestDto requestDto) {
        ResponseEntity<String> response = restTemplate.postForEntity(
                aiFlightServerUrl,
                requestDto,
                String.class
        );
        return response.getBody();
    }

    /**
     * (테스트용) '관광지 API' 직접 호출 메서드
     */
    public List<AttractionResponseDto> getAttractionsFromAi_FOR_TEST(AttractionRequestDto aiRequestDto) {
        AttractionResponseDto[] responseArray = restTemplate.postForObject(
                aiAttractionServerUrl,
                aiRequestDto,
                AttractionResponseDto[].class
        );
        if (responseArray == null) {
            return List.of();
        }
        return Arrays.asList(responseArray);
    }

    /**
     * (테스트용) '호텔 API' 직접 호출 메서드
     */
    public String getHotelSimulation_FOR_TEST(HotelRequestDto aiRequestDto) {
        ResponseEntity<String> response = restTemplate.postForEntity(
                aiHotelServerUrl,
                aiRequestDto,
                String.class

        );
        return response.getBody();
    }
}