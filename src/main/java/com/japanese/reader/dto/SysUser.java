package com.japanese.reader.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/**
 * 业务用户（与后台登录账号 admin_user 相互独立，为后续角色管理预留）。
 * 软删除：is_deleted=1，不做物理删除。
 */
@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 唯一性在业务层校验（仅限未删除记录），DB 不加 unique 以便软删除后重建同名用户 */
    @Column(nullable = false, length = 50)
    private String username;

    @JsonIgnore
    @Column(nullable = false, length = 60)
    private String passwordHash;

    @Column(length = 50)
    private String realName;

    /** ENABLED / DISABLED */
    private String status;

    @JsonIgnore
    private int isDeleted;

    private String createdAt;
    private String updatedAt;
}
