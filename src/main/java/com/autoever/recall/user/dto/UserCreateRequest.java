package com.autoever.recall.user.dto;

import com.autoever.recall.user.domain.UserCreateCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "이메일은 필수값입니다")
        @Email(message = "올바른 이메일 형식이 아닙니다")
        String email,

        @NotBlank(message = "이름은 필수값입니다")
        @Size(max = 20, message = "이름은 최대 20자입니다")
        String name,

        @Size(max = 11, message = "휴대폰 번호는 최대 11자입니다")
        @Pattern(regexp = "^01(?:0|1|[6-9])[0-9]{7,8}$", message = "올바른 휴대폰 번호 형식이 아닙니다")
        String phone,

        @Size(max = 255)
        String address
) {
    public UserCreateCommand toDomain() {
        return new UserCreateCommand(
                name.trim(),
                phone,
                address != null ? address.trim() : null
        );
    }
}
