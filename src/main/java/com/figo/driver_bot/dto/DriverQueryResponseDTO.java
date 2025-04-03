package com.figo.driver_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;

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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DriverProfile> getDriverProfiles() {
        return driverProfiles;
    }

    public void setDriverProfiles(List<DriverProfile> driverProfiles) {
        this.driverProfiles = driverProfiles;
    }

    public List<Park> getParks() {
        return parks;
    }

    public void setParks(List<Park> parks) {
        this.parks = parks;
    }

    @Override
    public String toString() {
        return "DriverQueryResponseDTO{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", total=" + total +
                ", driverProfiles=" + driverProfiles +
                ", parks=" + parks +
                '}';
    }

    public static class DriverProfile {
        @JsonProperty("updated_at")
        private OffsetDateTime updatedAt;

        @JsonProperty("accounts")
        private List<Account> accounts;

        @JsonProperty("car")
        private Car car;

        @JsonProperty("driver_profile")
        private DriverDetails driverProfile;

        public OffsetDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

        public List<Account> getAccounts() {
            return accounts;
        }

        public void setAccounts(List<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public String toString() {
            return "DriverProfile{" +
                    "updatedAt=" + updatedAt +
                    ", accounts=" + accounts +
                    ", car=" + car +
                    ", driverProfile=" + driverProfile +
                    '}';
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public DriverDetails getDriverProfile() {
            return driverProfile;
        }

        public void setDriverProfile(DriverDetails driverProfile) {
            this.driverProfile = driverProfile;
        }
    }

    public static class Account {
        @JsonProperty("id")
        private String id;

        public Account(String id) {
            this.id = id;
        }

        public Account() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }

    public static class Car {
        @JsonProperty("callsign")
        private String callsign;

        @JsonProperty("id")
        private String id;

        public String getCallsign() {
            return callsign;
        }

        public void setCallsign(String callsign) {
            this.callsign = callsign;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "callsign='" + callsign + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }


    public static class DriverDetails {
        @JsonProperty("id")
        private String id;

        @JsonProperty("driver_license")
        private DriverLicense driverLicense;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DriverLicense getDriverLicense() {
            return driverLicense;
        }

        public void setDriverLicense(DriverLicense driverLicense) {
            this.driverLicense = driverLicense;
        }

        @Override
        public String toString() {
            return "DriverDetails{" +
                    "id='" + id + '\'' +
                    ", driverLicense=" + driverLicense +
                    '}';
        }
    }

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

        public OffsetDateTime getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(OffsetDateTime birthDate) {
            this.birthDate = birthDate;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public OffsetDateTime getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(OffsetDateTime expirationDate) {
            this.expirationDate = expirationDate;
        }

        public OffsetDateTime getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(OffsetDateTime issueDate) {
            this.issueDate = issueDate;
        }

        public String getNormalizedNumber() {
            return normalizedNumber;
        }

        public void setNormalizedNumber(String normalizedNumber) {
            this.normalizedNumber = normalizedNumber;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "DriverLicense{" +
                    "birthDate=" + birthDate +
                    ", country='" + country + '\'' +
                    ", expirationDate=" + expirationDate +
                    ", issueDate=" + issueDate +
                    ", normalizedNumber='" + normalizedNumber + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    public static class Park {
        @JsonProperty("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Park{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
