package com.japanese.reader.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    /** BCrypt 哈希，永不返回给前端 */
    @JsonIgnore
    @Column(nullable = false, length = 60)
    private String passwordHash;

    private String createdAt;
}
