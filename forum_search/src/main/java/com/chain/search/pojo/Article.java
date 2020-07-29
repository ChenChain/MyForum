package com.chain.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * TODO
 * 文章实体类 -- 对应es中搜索出来的页面 显示的内容
 * @author chain
 * @date 2020/6/2
 */
//对应es中的文档  对应 索引 + 类别
@Data
@Document(indexName = "forum_article",type = "article")
public class Article  implements Serializable {

    @Id
    private String id;


    /**
     * true 为索引
     * 是否索引，就是看该域是否能被搜索
     * 是否分词，就是看该词是所有匹配还是单词匹配
     * 是否存储。就是是否在页面上显示
     */
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String title;
    @Field(index = true,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 文章审核状态
     */
    private String state;
}
