package com.figo.driver_bot.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class DriverQueryRequestDTO {

    @JsonProperty("query")
    private Query query;

    @JsonProperty("fields")
    private Map<String, List<String>> fields;

    @Data
    public static class Query {
        @JsonProperty("park")
        private Park park;

        @JsonProperty("text")
        private String text;
    }

    @Data
    public static class Park {
        @JsonProperty("id")
        private String id;

        @JsonProperty("driver_profile")
        private DriverProfile driverProfile;
    }

    @Data
    public static class DriverProfile {
        @JsonProperty("work_status")
        private List<String> workStatus;
    }
}
