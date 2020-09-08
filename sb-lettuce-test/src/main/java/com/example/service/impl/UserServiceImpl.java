package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.pojo.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate redisTemplate;

    @Override
    public void putIn(String key) {
        redisTemplate.opsForValue().set(key, "你好");
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
    }

    @Override
    public User getMap(String key) {
        User user = null;
        if(redisTemplate.hasKey(key)) {
            logger.info("缓存中有数据，从缓存中取出");
            Map userMap = redisTemplate.opsForHash().entries(key);
            user = JSON.parseObject(JSON.toJSONString(userMap), User.class);
        }else {
            logger.info("缓存中没有数据，从数据库中取出");
            user = new User("小明", "18", "玩游戏");
            logger.info("存入缓存");
            String userString = JSON.toJSONString(user);
            Map map = JSON.parseObject(userString, Map.class);
            redisTemplate.opsForHash().putAll(key, map);
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
            logger.info("放入缓存成功");
        }
        return user;
    }
}
