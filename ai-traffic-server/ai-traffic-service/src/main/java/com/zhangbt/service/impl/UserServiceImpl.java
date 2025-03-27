package com.zhangbt.service.impl;

import com.zhangbt.common.exception.BaseException;
import com.zhangbt.dao.UserMapper;
import com.zhangbt.domain.dto.UserLoginDTO;
import com.zhangbt.domain.entity.UserEntity;
import com.zhangbt.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUser() {
        return userMapper.getAllUsers();
    }

    @Override
    public UserEntity login(UserLoginDTO userLoginDTO) {

        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        UserEntity userEntity = userMapper.getByUsername(username);

        // 各种异常处理
        if (userEntity == null) {
            // 账号不存在
            throw new BaseException("账号不存在!");
        }

        // 密码对比
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(userEntity.getPassword())) {
            // 密码错误
            throw new BaseException("密码错误!");
        }

        if (userEntity.getStatus() == 0) {
            // 账号被封禁
            throw new BaseException("账号被封禁!");
        }

        return userEntity;
    }
}
