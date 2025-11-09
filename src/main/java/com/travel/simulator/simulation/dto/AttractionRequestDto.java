package com.travel.simulator.simulation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor  // 모든 필드를 받는 생성자 자동 생성
@NoArgsConstructor   // Jackson 역직렬화를 위한 기본 생성자 추가 (필수)
public class AttractionRequestDto {

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    @JsonProperty("theme")
    private String theme;

    @JsonProperty("count")
    private int count;

    @JsonProperty("use_api")
    private boolean useApi;  // JSON은 use_api지만, 자바에서는 camelCase로
}
