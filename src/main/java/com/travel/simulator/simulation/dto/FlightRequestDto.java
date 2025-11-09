package com.travel.simulator.simulation.dto;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor




public class FlightRequestDto {
    // AI 팀원이 줬던 JSON key("departure_airport")와
    // Java 변수명(departureAirport)을 연결합니다.
    @JsonProperty("departure_airport")
    private String departureAirport;

    @JsonProperty("arrival_airport")
    private String arrivalAirport;

    @JsonProperty("departure_date")
    private String departureDate;

    @JsonProperty("arrival_date")
    private String arrivalDate;

    private String adults;
    private String children;
    private String infants;

    @JsonProperty("cabin_class")
    private String cabinClass;

    private String currency;

    @JsonProperty("top_n")
    private int topN;

    private boolean use_api;
}