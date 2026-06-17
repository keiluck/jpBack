package com.japanese.reader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 移动端登录请求 DTO
 */
@Data
@Schema(description = "移动端登录请求")
public class MobileLoginDTO {

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名", example = "hanako")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", example = "123456")
    private String password;
}
