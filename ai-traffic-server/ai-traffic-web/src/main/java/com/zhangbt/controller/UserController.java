package com.zhangbt.controller;

import com.zhangbt.common.properties.JwtProperties;
import com.zhangbt.common.result.Result;
import com.zhangbt.common.utils.CustomCache;
import com.zhangbt.common.utils.JwtUtil;
import com.zhangbt.dao.UserMapper;
import com.zhangbt.domain.dto.UserLoginDTO;
import com.zhangbt.domain.entity.UserEntity;
import com.zhangbt.domain.vo.UserLoginVO;
import com.zhangbt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtProperties jwtProperties;

    private final CustomCache<String, Object> customCache;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        UserEntity userEntity = userService.login(userLoginDTO);

        // 登陆成功后，生成jwt令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId", userEntity.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims
        );


        UserLoginVO userLoginVO = UserLoginVO.builder()
                .Id(userEntity.getId())
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .token(token)
                .build();
        customCache.put("user", userLoginVO, 5000);
        log.info("cache值:{}", customCache.get("user").toString());

        return Result.success(userLoginVO);
    }

}
