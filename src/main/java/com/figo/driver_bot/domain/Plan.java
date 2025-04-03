package com.figo.driver_bot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String yandexId;
    @Column(nullable = false)
    private Boolean isCurrentActive;
    private LocalDateTime updatedAt = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    @Column(nullable = false)
    private Long updatedBy;
    private String updateAction;

    public Plan() {
    }

    public Plan(Integer id, String name, String yandexId, Boolean isCurrentActive, LocalDateTime updatedAt, Long updatedBy, String updateAction) {
        this.id = id;
        this.name = name;
        this.yandexId = yandexId;
        this.isCurrentActive = isCurrentActive;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.updateAction = updateAction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getYandexId() {
        return yandexId;
    }

    public void setYandexId(@NotBlank String yandexId) {
        this.yandexId = yandexId;
    }

    public Boolean getCurrentActive() {
        return isCurrentActive;
    }

    public void setCurrentActive(Boolean currentActive) {
        isCurrentActive = currentActive;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yandexId='" + yandexId + '\'' +
                ", isCurrentActive=" + isCurrentActive +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + updatedBy +
                ", updateAction='" + updateAction + '\'' +
                '}';
    }
}
