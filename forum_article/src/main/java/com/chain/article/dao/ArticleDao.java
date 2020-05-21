package com.chain.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chain.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 审核文章
     */
    @Modifying
    @Query(value = "update tb_article set state =1 where  id = ?",nativeQuery = true)
    void updateState(String id);

    /**
     * 点赞数加1
     */
    @Modifying
    @Query(value = "update  tb_article set  thumbup =thumbup+1 where  id =? ",nativeQuery = true)
    void addThumbUp(String id);


}
