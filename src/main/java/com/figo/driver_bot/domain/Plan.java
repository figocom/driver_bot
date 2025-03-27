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
public class Plan {
    @Id
    private Integer id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String yandexId;
    @NotBlank
    @Column(nullable = false)
    private Boolean isCurrentActive;
    private LocalDateTime updatedAt= ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    @NotBlank
    @Column(nullable = false)
    private Integer updatedBy;
    private String updateAction;
}
