package com.zhangbt.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String password;
    private Integer auth;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
