package com.zhangbt.test;

import com.zhangbt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@Slf4j
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void getAllUsers() {
        log.info("getAllUsers:{}", userService.getAllUser());
    }

    @Test
    public void getUserPassWord() {
        log.info(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

}
