package com.example;

import com.alibaba.fastjson.JSON;
import com.example.pojo.Article;
import com.example.service.ListCacheService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ListRedisTemplateTest {
    Logger logger = LoggerFactory.getLogger(ListRedisTemplateTest.class);

    @Autowired
    private ListCacheService listCacheService;

    @Test
    public void initArticle() {
        listCacheService.initArticle();
    }

    @Test
    public void getArticleTop5() {
        List<Article> articleTop5 = listCacheService.getArticleTop5();
        logger.info("前五篇文章信息为：" + JSON.toJSONString(articleTop5));
    }

    @Test
    public void addArticle() {
        listCacheService.addArticle();
    }

    @Test
    public void delArticle() {
        listCacheService.delArticle();
    }
}
