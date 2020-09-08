package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.pojo.Article;
import com.example.service.ListCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ListCacheServiceImpl implements ListCacheService {
    Logger logger = LoggerFactory.getLogger(ListCacheServiceImpl.class);

    @Autowired
    @Qualifier("redisTemplate01")
    private RedisTemplate redisTemplate;

    //研究一下这个是怎么注入的
    @Resource(name = "redisTemplate01")
    private ListOperations<String, Article> operationList;

    /**
     * 初始化，在队列中存入5篇文章
     */
    @Override
    public void initArticle() {
        String key = "article:top5";
        List<Article> articleList = new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            Article article = new Article();
            article.setId("1000" + i);
            article.setTitle("文章标题0" + i);
            article.setContent("文章正文-0" + i);
            article.setAuthor("君辉");
            article.setClickNum(new Random().nextInt(1000));
            article.setCrtTime("2020-08-01");
            articleList.add(article);
        }
        operationList.rightPushAll(key, articleList);
        redisTemplate.expire(key, 3600, TimeUnit.SECONDS);
        logger.info("初始化成功！");
    }

    /**
     * 获取缓存中前五篇文章
     * @return
     */
    @Override
    public List<Article> getArticleTop5() {
        String key = "article:top5";
        return operationList.range(key, 0, 4);
    }

    /**
     * 向缓存list中添加一篇文章的信息
     * @return
     */
    @Override
    public Long addArticle() {
        String key = "article:top5";
        logger.info("模拟数据库中添加文章信息，同步到缓存中");
        Article article = new Article("1000" + 6, "一本好书", "内容很精彩",
                "萌萌", "2020-08-02", new Random().nextInt(1000));
        Long result = operationList.leftPush(key, article);
        logger.info("添加成功!");
        return result;
    }

    /**
     * 向缓存list中删除一篇文章的信息
     * @return
     */
    @Override
    public void delArticle() {
        String key = "article:top5";
        logger.info("模拟数据库中删除文章信息，同步到缓存中");
        operationList.rightPop(key);
        //有点问题：如果获取返回值则会报fastJson转换异常。
        logger.info("删除过期文章成功！");
    }
}
