package com.travel.simulator.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequestDto {

    // 출발 날짜
    @JsonProperty("departure_date")
    private String departureDate;

    // 도착 날짜
    @JsonProperty("arrival_date")
    private String arrivalDate;

    // 관광지 리스트
    @JsonProperty("attractions")  // 👈 명시적으로 붙이는 게 안전합니다
    private List<AttractionResponseDto> attractions;
}
