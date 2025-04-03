package com.figo.driver_bot.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;


public class DriverQueryRequestDTO {

    @JsonProperty("query")
    private Query query;

    @JsonProperty("fields")
    private Map<String, List<String>> fields;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Map<String, List<String>> getFields() {
        return fields;
    }

    public void setFields(Map<String, List<String>> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "DriverQueryRequestDTO{" +
                "query=" + query +
                ", fields=" + fields +
                '}';
    }

    public static class Query {
        @JsonProperty("park")
        private Park park;

        @JsonProperty("text")
        private String text;



        public Park getPark() {
            return park;
        }

        public void setPark(Park park) {
            this.park = park;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "park=" + park +
                    ", text='" + text + '\'' +
                    '}';
        }
    }


    public static class Park {
        @JsonProperty("id")
        private String id;

        @JsonProperty("driver_profile")
        private DriverProfile driverProfile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DriverProfile getDriverProfile() {
            return driverProfile;
        }

        public void setDriverProfile(DriverProfile driverProfile) {
            this.driverProfile = driverProfile;
        }

        @Override
        public String toString() {
            return "Park{" +
                    "id='" + id + '\'' +
                    ", driverProfile=" + driverProfile +
                    '}';
        }
    }


    public static class DriverProfile {
        @JsonProperty("work_status")
        private List<String> workStatus;

        public List<String> getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(List<String> workStatus) {
            this.workStatus = workStatus;
        }

        @Override
        public String toString() {
            return "DriverProfile{" +
                    "workStatus=" + workStatus +
                    '}';
        }
    }
}
