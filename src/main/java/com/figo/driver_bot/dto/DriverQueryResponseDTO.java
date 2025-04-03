package com.figo.driver_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class DriverQueryResponseDTO {

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("offset")
    private int offset;

    @JsonProperty("total")
    private int total;

    @JsonProperty("driver_profiles")
    private List<DriverProfile> driverProfiles;

    @JsonProperty("parks")
    private List<Park> parks;

    @Data
    public static class DriverProfile {
        @JsonProperty("updated_at")
        private OffsetDateTime updatedAt;

        @JsonProperty("accounts")
        private List<Account> accounts;

        @JsonProperty("car")
        private Car car;

        @JsonProperty("driver_profile")
        private DriverDetails driverProfile;
    }

    @Data
    public static class Account {
        @JsonProperty("id")
        private String id;
    }

    @Data
    public static class Car {
        @JsonProperty("callsign")
        private String callsign;

        @JsonProperty("id")
        private String id;
    }

    @Data
    public static class DriverDetails {
        @JsonProperty("id")
        private String id;

        @JsonProperty("driver_license")
        private DriverLicense driverLicense;
    }

    @Data
    public static class DriverLicense {
        @JsonProperty("birth_date")
        private OffsetDateTime birthDate;

        @JsonProperty("country")
        private String country;

        @JsonProperty("expiration_date")
        private OffsetDateTime expirationDate;

        @JsonProperty("issue_date")
        private OffsetDateTime issueDate;

        @JsonProperty("normalized_number")
        private String normalizedNumber;

        @JsonProperty("number")
        private String number;
    }

    @Data
    public static class Park {
        @JsonProperty("id")
        private String id;
    }
}
