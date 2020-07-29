package com.chain.article.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "tb_article")
public class Article implements Serializable {

    @Id
    private String id;//ID

    @Column(name = "column_id")
    private String columnId;//专栏ID

    @Column(name = "user_id")
    private String userId;//用户ID
    @Column(name = "title")
    private String title;//标题
    @Column(name = "content")
    private String content;//文章正文
    @Column(name = "image")
    private String image;//文章封面
    @Column(name = "create_time")
    private java.util.Date createTime;//发表日期
    @Column(name = "update_time")
    private java.util.Date updateTime;//修改日期
    @Column(name = "need_public")
    private boolean needPublic;//是否公开
    @Column(name = "need_top")
    private boolean needTop;//是否置顶
    @Column(name = "visit")
    private Integer visit;//浏览量
    @Column(name = "thumb_up")
    private Integer thumbUp;//点赞数
    @Column(name = "comment")
    private Integer comment;//评论数
    @Column(name = "state")
    private String state;//审核状态
    @Column(name = "channel_id")
    private String channelId;//所属频道
    @Column(name = "url")
    private String url;//URL
    @Column(name = "type")
    private String type;//类型


}
