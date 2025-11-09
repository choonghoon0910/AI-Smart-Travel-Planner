package com.travel.simulator.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * AI 서버로부터 '숙소 제안' 응답(JSON)을 받기 위한 DTO (백엔드용)
 * 안드로이드의 DTO와 필드 구조가 일치합니다.
 */
@Getter
@NoArgsConstructor // JSON -> Java 객체 변환을 위해 기본 생성자 필요
public class HotelResponseDto {

    @JsonProperty("hotel_name")
    private String hotelName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("price_per_night")
    private String pricePerNight;

    // (LOMbok @Getter가 모든 getXxx() 메서드를 자동 생성해줍니다)
}