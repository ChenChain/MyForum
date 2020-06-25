package com.chain.search.service;

import com.chain.search.dao.ArticleDao;
import com.chain.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * TODO
 *
 * @author chain
 * @date 2020/6/2
 */
@Service
public class ArticleService {


    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;


    public void save(Article article) {
//        article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }

    /**
     * 根据关键字来查询文章结果
     *
     * @param key
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable= PageRequest.of(page-1,size);

           return articleDao.findByTitleOrContentLike(key,key,pageable);
    }


}
