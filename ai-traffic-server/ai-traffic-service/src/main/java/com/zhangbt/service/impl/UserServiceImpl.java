package com.zhangbt.service.impl;

import com.zhangbt.dao.UserMapper;
import com.zhangbt.domain.entity.UserEntity;
import com.zhangbt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUser() {
        return userMapper.getAllUsers();
    }
}
