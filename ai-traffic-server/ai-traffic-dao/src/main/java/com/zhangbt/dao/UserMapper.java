package com.zhangbt.dao;

import com.zhangbt.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> getAllUsers();

}
