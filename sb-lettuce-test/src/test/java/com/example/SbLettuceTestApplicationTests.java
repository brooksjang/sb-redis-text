package com.example;

import com.example.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbLettuceTestApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    public void putIn() {
        userService.putIn("哈哈");
    }


    @Test
    public void getMap() {
        userService.getMap("王钢蛋");
    }
}
