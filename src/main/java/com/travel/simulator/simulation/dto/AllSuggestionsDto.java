package com.travel.simulator.simulation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor // 3개의 리스트를 받는 생성자
public class AllSuggestionsDto {

    // ⚠️ 참고: FlightResponseDto, HotelResponseDto도
    //        simulation/dto에 새로 만들어야 합니다!
    // (AttractionResponseDto는 이미 만들었습니다)

    private List<FlightResponseDto> flights;
    private List<AttractionResponseDto> attractions;
    private List<HotelResponseDto> hotels;
}