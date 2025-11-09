package com.travel.simulator.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AI 서버로부터 '항공권 제안' 응답(JSON)을 받기 위한 DTO (백엔드용)
 * 안드로이드의 DTO와 필드 구조가 일치합니다.
 */
@Getter
@NoArgsConstructor // JSON -> Java 객체 변환을 위해 기본 생성자 필요
public class FlightResponseDto {

    // JSON key "airline_name"을 Java 필드 "airlineName"에 매핑
    @JsonProperty("airline_name")
    private String airlineName;

    @JsonProperty("flight_info")
    private String flightInfo;

    @JsonProperty("price")
    private String price;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("departure_airport")
    private String departureAirport;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("arrival_airport")
    private String arrivalAirport;

    // (Lombok @Getter가 모든 getXxx() 메서드를 자동 생성해줍니다)
}