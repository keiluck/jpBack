package com.japanese.reader.dto;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 移动端用户实体
 */
@Entity
@Table(name = "mobile_user")
public class MobileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 50)
    private String nickname;

    @Column(length = 500)
    private String avatar;

    @Column(length = 20, unique = true)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer level = 1;

    /** 状态：1启用 0禁用 */
    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // -------- Getters & Setters --------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // -------- Builder --------

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final MobileUser user = new MobileUser();
        public Builder username(String v) { user.username = v; return this; }
        public Builder password(String v) { user.password = v; return this; }
        public Builder nickname(String v) { user.nickname = v; return this; }
        public Builder phone(String v) { user.phone = v; return this; }
        public Builder email(String v) { user.email = v; return this; }
        public Builder level(Integer v) { user.level = v; return this; }
        public Builder status(Integer v) { user.status = v; return this; }
        public MobileUser build() { return user; }
    }
}
