package com.zhangbt.dao;

import com.zhangbt.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> getAllUsers();

    @Select("select * from user where username = #{username}")
    UserEntity getByUsername(String username);
}
