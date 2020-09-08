package com.example.service;

import com.example.pojo.Article;

import java.util.List;

public interface ListCacheService {

    void initArticle();

    List<Article> getArticleTop5();

    Long addArticle();

    void delArticle();
}
