package com.figo.driver_bot.domain;


import jakarta.persistence.*;
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
}
