package com.figo.driver_bot.service;

import com.figo.driver_bot.dto.GetOneDriverResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DriverUpdateService {
    private final RestTemplate restTemplate;

    public DriverUpdateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GetOneDriverResponse getOneDriver(String driverId) {
        String url = "https://fleet-api.taxi.yandex.net/v2/parks/contractors/driver-profile?contractor_profile_id=" + driverId;
        HttpEntity<String> entity = new HttpEntity<>(getHeader());
        ResponseEntity<GetOneDriverResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                GetOneDriverResponse.class
        );
        return response.getBody();
    }


    public ResponseEntity<String> updateDriverProfile(GetOneDriverResponse request, String driverId) {
        String url = "https://fleet-api.taxi.yandex.net/v2/parks/contractors/driver-profile?contractor_profile_id=" + driverId;
        HttpEntity<GetOneDriverResponse> entity = new HttpEntity<>(request, getHeader());
        return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Language", "RU");
        headers.set("X-Client-ID", "taxi/park/42a72aafd8ad403c9858452306872db7");
        headers.set("X-API-Key", "YMEWPiDCvICShctdnqkKaEWmTwIVZKGFuqDQMw");
        headers.set("X-Park-ID", "42a72aafd8ad403c9858452306872db7");
        return headers;
    }
}
