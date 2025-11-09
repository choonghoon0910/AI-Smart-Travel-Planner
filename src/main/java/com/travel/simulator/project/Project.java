package com.travel.simulator.project; // 👈 project 패키지

import com.travel.simulator.simulation.dto.ProjectRequestDto; // 👈 1. 안드로이드가 보낸 DTO 임포트
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다
@Entity // "이 클래스는 DB 테이블입니다"
@Table(name = "projects") // "테이블 이름은 'projects'로 해주세요"
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String selectedFlight; // (선택한 항공권 JSON 문자열)

    @Column(length = 4096)
    private String selectedAttractions; // (선택한 관광지 목록 JSON 문자열)

    @Column(length = 2048)
    private String selectedHotel;

    // --- 2. 안드로이드에서 받은 모든 데이터를 필드로 선언 ---
    private String travelName;
    private String city;
    private String country;
    private String startDate;
    private String endDate;
    private String departureAirport;
    private String cabinClass;
    private String adults;
    private String children;
    private String infants;
    private String theme;

    // (나중에 user 필드를 추가해서 '누가' 쓴 글인지 연결할 수 있습니다)

    /**
     * 3. 'DTO(주문서)'를 받아서 'Entity(DB용 객체)'로 변환하는 생성자
     */
    public Project(ProjectRequestDto requestDto) {
        this.travelName = requestDto.getTravelName();
        this.city = requestDto.getCity();
        this.country = requestDto.getCountry();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.departureAirport = requestDto.getDepartureAirport();
        this.cabinClass = requestDto.getCabinClass();
        this.adults = requestDto.getAdults();
        this.children = requestDto.getChildren();
        this.infants = requestDto.getInfants();
        this.theme = requestDto.getTheme();
    }
}