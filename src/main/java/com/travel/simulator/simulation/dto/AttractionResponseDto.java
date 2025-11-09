package com.travel.simulator.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AttractionResponseDto {

    // 영어 이름
    @JsonProperty("name_en")
    private String nameEn;

    // 한국어 이름
    @JsonProperty("name_ko")
    private String nameKo;

    // 주소
    @JsonProperty("address")
    private String address;

    // 관광지 타입 (예: 박물관, 명소 등)
    @JsonProperty("type")
    private String type;

    // 예상 비용 (KRW)
    @JsonProperty("estimated_cost_krw")
    private int estimatedCostKrw;

    // 예상 소요 시간 (시간 단위)
    @JsonProperty("suggested_duration_hours")
    private double suggestedDurationHours;

    // 설명
    @JsonProperty("description")
    private String description;

    // 위도(latitude)
    @JsonProperty("lat")
    private double lat;

    // 경도(longitude)
    @JsonProperty("lng")
    private double lng;
}
