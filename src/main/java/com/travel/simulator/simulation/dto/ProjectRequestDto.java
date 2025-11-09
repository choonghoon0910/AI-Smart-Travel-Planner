package com.travel.simulator.simulation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {
    // (이 필드명은 안드로이드의 DTO와 일치해야 합니다)
    private String travelName;
    private String city; // (안드로이드의 destination)
    private String country;
    private String startDate;
    private String endDate;
    private String departureAirport;
    private String cabinClass;
    private String adults;
    private String children;
    private String infants;
    private String theme;
}