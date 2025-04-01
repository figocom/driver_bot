package com.figo.driver_bot.service;

import com.figo.driver_bot.dto.YandexPlanDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class YandexApiClient {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://fleet-api.taxi.yandex.net/v1/parks/driver-work-rules?park_id=42a72aafd8ad403c9858452306872db7";

    public YandexApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<YandexPlanDto> getCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Language", "RU");
        headers.set("X-Client-ID", "taxi/park/42a72aafd8ad403c9858452306872db7");
        headers.set("X-API-Key", "YMEWPiDCvICShctdnqkKaEWmTwIVZKGFuqDQMw");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map<String, List<YandexPlanDto>>> response = restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        if (response.getBody() != null) {
            Map<String, List<YandexPlanDto>> body = response.getBody();
            if (body.containsKey("rules")) {
                return body.get("rules");
            }
        }
        return List.of();
    }
}
