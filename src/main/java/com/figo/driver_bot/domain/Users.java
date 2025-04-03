package com.figo.driver_bot.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
public class Users {
    @Id
    private Long id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String chatId;
    private String username;
    private String nickname;

    @Column(nullable = false)
    private Boolean isAdmin = false;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt = ZonedDateTime.now(ZoneId.of("Asia/Tashkent")).toLocalDateTime();
    private LocalDateTime updatedAt;
    private Long updatedBy;
    private String updateAction;

    public Users() {
    }

    public Users(Long id, String chatId, String username, String nickname, Boolean isAdmin, LocalDateTime createdAt,
                 LocalDateTime updatedAt, Long updatedBy, String updateAction) {
        this.id = id;
        this.chatId = chatId;
        this.username = username;
        this.nickname = nickname;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.updateAction = updateAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getChatId() {
        return chatId;
    }

    public void setChatId(@NotBlank String chatId) {
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return "Users{" +
                "id=" + id +
                ", chatId='" + chatId + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", isAdmin=" + isAdmin +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + updatedBy +
                ", updateAction='" + updateAction + '\'' +
                '}';
    }
}
