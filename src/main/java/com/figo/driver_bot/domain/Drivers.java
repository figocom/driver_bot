package com.figo.driver_bot.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Drivers {
    @Id
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
    private Integer currentPlanId;
    @NotBlank
    @Column(nullable = false)
    private String nextPlan;
    @NotBlank
    @Column(nullable = false)
    private Integer nextPlanId;
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
    private Integer updatedBy;
    private String updateAction;
}
