package com.blue.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Data
@Validated
public class User {
    @NotNull
    private Integer id;

    @NotNull
    @Pattern(regexp = "^\\S{4,16}$")
    private String username;

    @JsonIgnore
    private String password;

    @NotNull
    @Pattern(regexp = "^\\S{1,20}$")
    private String nickname;

    @Email
    private String email;

    @URL
    private String avatarUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
