package com.chain.search.dao;

import com.chain.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * TODO
 *
 * @author chain
 * @date 2020/6/2
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    /**
     * 分页
     * @param title
     * @param content
     * @param pageable  分页封装
     * @return
     */
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);

}
