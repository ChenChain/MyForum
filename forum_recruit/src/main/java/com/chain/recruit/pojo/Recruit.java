package com.chain.recruit.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类 招聘信息
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name = "tb_recruit")
public class Recruit implements Serializable {

    @Id
    private String id;//ID


    @Column(name = "job_name")
    private String jobName;//职位名称
    @Column(name = "salary")

    private String salary;//薪资范围
    @Column(name = "condition")

    private String condition;//经验要求
    @Column(name = "education")

    private String education;//学历要求
    @Column(name = "type")

    private String type;//任职方式
    @Column(name = "address")

    private String address;//办公地址
    @Column(name = "eid")

    private String eid;//企业ID
    @Column(name = "create_time")

    private java.util.Date createTime;//创建日期

    @Column(name = "state")
    private String state;//状态
    @Column(name = "url")

    private String url;//网址
    @Column(name = "label")

    private String label;//标签
    @Column(name = "content1")

    private String content1;//职位描述
    @Column(name = "content2")

    private String content2;//职位要求

}
