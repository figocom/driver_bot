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
public class Users {
    @Id
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String chatId;
    private String username;
    private String nickname;
    @NotBlank
    @Column(nullable = false)
    private Boolean isAdmin=false;
    @NotBlank
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt= ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    private LocalDateTime updatedAt;
    private Integer updatedBy;
    private String updateAction;
}
