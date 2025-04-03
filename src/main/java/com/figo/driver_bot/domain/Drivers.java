package com.figo.driver_bot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String driverCardNumber;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String driverId;
    @NotBlank
    @Column(nullable = false)
    private String currentPlan;
    @NotBlank
    @Column(nullable = false)
    private String currentPlanId;
    @NotBlank
    @Column(nullable = false)
    private String nextPlan;
    @NotBlank
    @Column(nullable = false)
    private String nextPlanId;
    @NotBlank
    @Column(nullable = false)
    private LocalDateTime startedAt= ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    @NotBlank
    @Column(nullable = false)
    private LocalDateTime endWill;
    @NotBlank
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @NotBlank
    @Column(nullable = false)
    private Long updatedBy;
    private String updateAction;

    public Drivers() {
    }

    public Drivers(Integer id, String driverCardNumber, String driverId, String currentPlan, String currentPlanId, String nextPlan, String nextPlanId, LocalDateTime startedAt, LocalDateTime endWill, LocalDateTime updatedAt, Long updatedBy, String updateAction) {
        this.id = id;
        this.driverCardNumber = driverCardNumber;
        this.driverId = driverId;
        this.currentPlan = currentPlan;
        this.currentPlanId = currentPlanId;
        this.nextPlan = nextPlan;
        this.nextPlanId = nextPlanId;
        this.startedAt = startedAt;
        this.endWill = endWill;
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

    public @NotBlank String getDriverCardNumber() {
        return driverCardNumber;
    }

    public void setDriverCardNumber(@NotBlank String driverCardNumber) {
        this.driverCardNumber = driverCardNumber;
    }

    public @NotBlank String getDriverId() {
        return driverId;
    }

    public void setDriverId(@NotBlank String driverId) {
        this.driverId = driverId;
    }

    public @NotBlank String getCurrentPlan() {
        return currentPlan;
    }

    public void setCurrentPlan(@NotBlank String currentPlan) {
        this.currentPlan = currentPlan;
    }

    public @NotBlank String getCurrentPlanId() {
        return currentPlanId;
    }

    public void setCurrentPlanId(@NotBlank String currentPlanId) {
        this.currentPlanId = currentPlanId;
    }

    public @NotBlank String getNextPlan() {
        return nextPlan;
    }

    public void setNextPlan(@NotBlank String nextPlan) {
        this.nextPlan = nextPlan;
    }

    public @NotBlank String getNextPlanId() {
        return nextPlanId;
    }

    public void setNextPlanId(@NotBlank String nextPlanId) {
        this.nextPlanId = nextPlanId;
    }

    public @NotBlank LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(@NotBlank LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public @NotBlank LocalDateTime getEndWill() {
        return endWill;
    }

    public void setEndWill(@NotBlank LocalDateTime endWill) {
        this.endWill = endWill;
    }

    public @NotBlank LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NotBlank LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public @NotBlank Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(@NotBlank Long updatedBy) {
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
        return "Drivers{" +
                "id=" + id +
                ", driverCardNumber='" + driverCardNumber + '\'' +
                ", driverId='" + driverId + '\'' +
                ", currentPlan='" + currentPlan + '\'' +
                ", currentPlanId='" + currentPlanId + '\'' +
                ", nextPlan='" + nextPlan + '\'' +
                ", nextPlanId='" + nextPlanId + '\'' +
                ", startedAt=" + startedAt +
                ", endWill=" + endWill +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + updatedBy +
                ", updateAction='" + updateAction + '\'' +
                '}';
    }
}
