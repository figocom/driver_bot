package com.figo.driver_bot.service;
import com.figo.driver_bot.dto.DriverQueryRequestDTO;
import com.figo.driver_bot.dto.DriverQueryResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DriverProfileService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://fleet-api.taxi.yandex.net/v1/parks/driver-profiles/list";

    public DriverProfileService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DriverQueryResponseDTO getDriverProfiles(DriverQueryRequestDTO request) {
        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Language", "en-US");
        headers.set("X-Client-ID", "taxi/park/42a72aafd8ad403c9858452306872db7");
        headers.set("X-API-Key", "YMEWPiDCVlCShctdnqkKaEWmTwVZKGFuqDQMw");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Request entity
        org.springframework.http.HttpEntity<DriverQueryRequestDTO> entity =
                new org.springframework.http.HttpEntity<>(request, headers);

        // API Call
        ResponseEntity<DriverQueryResponseDTO> response = restTemplate.exchange(
                URL, HttpMethod.POST, entity, DriverQueryResponseDTO.class);

        return response.getBody();
    }
}
