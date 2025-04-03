package com.figo.driver_bot.dto;



public class YandexPlanDto {
    private String id;
    private String name;
    private boolean is_enabled;

    public YandexPlanDto() {
    }

    public YandexPlanDto(String id, String name, boolean is_enabled) {
        this.id = id;
        this.name = name;
        this.is_enabled = is_enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_enabled() {
        return is_enabled;
    }

    public void setIs_enabled(boolean is_enabled) {
        this.is_enabled = is_enabled;
    }

    @Override
    public String toString() {
        return "YandexPlanDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", is_enabled=" + is_enabled +
                '}';
    }
}
