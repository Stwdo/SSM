package com.example.ssm;

import com.example.ssm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsmApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        userMapper.findAll().forEach(System.out::println);
    }

}
