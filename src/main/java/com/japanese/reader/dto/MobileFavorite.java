package com.japanese.reader.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 移动端收藏实体
 */
@Entity
@Table(name = "mobile_favorite",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "target_id", "target_type"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    /**
     * 收藏类型：article
     */
    @Column(name = "target_type", nullable = false, length = 20)
    private String targetType;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
