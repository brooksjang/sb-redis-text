package com.example.service;

import com.example.pojo.User;

public interface UserService {
    //操作String类型
    void putIn(String key);

    //操作Map类型
    User getMap(String key);

}
