package com.chain.qa.pojo;

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
@Entity
@Table(name = "tb_problem")
@Data
public class Problem implements Serializable {

    @Id
    private String id;//ID


    @Column(name = "title")
    private String title;//标题
    @Column(name = "content")

    private String content;//内容
    @Column(name = "create_time")

    private java.util.Date createTime;//创建日期
    @Column(name = "update_time")

    private java.util.Date updateTime;//修改日期
    @Column(name = "user_id")

    private String userId;//用户ID
    @Column(name = "nickname")

    private String nickname;//昵称
    @Column(name = "visits")

    private Long visits;//浏览量
    @Column(name = "thumb_up")

    private Long thumbUp;//点赞数
    @Column(name = "reply")

    private Long reply;//回复数
    @Column(name = "solve")

    private String solve;//是否解决
    @Column(name = "reply_name")

    private String replyName;//回复人昵称
    @Column(name = "reply_time")
    private java.util.Date replyTime;//回复日期


}
