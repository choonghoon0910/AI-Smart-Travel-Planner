package com.travel.simulator.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // "이 파일은 설정 파일입니다."
public class RestTemplateConfig {

    @Bean // "이 RestTemplate 객체를 Spring이 관리하도록 등록합니다."
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}