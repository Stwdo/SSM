package com.example.ssm.mapper;


import com.example.ssm.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {

    int deleteById(Integer id);
    int save(User user);

    int updateById(User user);
    List<User> findAll();
    User findOne(Integer id);
}

