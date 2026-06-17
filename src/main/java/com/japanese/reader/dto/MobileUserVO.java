package com.japanese.reader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 移动端用户信息返回 VO
 */
@Data
@Schema(description = "移动端用户信息")
public class MobileUserVO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像URL")
    private String avatar;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "日语等级 1-5")
    private Integer level;

    @Schema(description = "登录Token")
    private String token;

    @Schema(description = "注册时间")
    private LocalDateTime createdAt;
}
