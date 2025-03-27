package com.zhangbt.service;

import com.zhangbt.domain.dto.UserLoginDTO;
import com.zhangbt.domain.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    public List<UserEntity> getAllUser();

    UserEntity login(UserLoginDTO userLoginDTO);
}
