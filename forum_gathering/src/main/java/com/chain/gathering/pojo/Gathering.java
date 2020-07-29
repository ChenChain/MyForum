package com.chain.gathering.pojo;

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
@Table(name = "tb_gathering")
public class Gathering implements Serializable {

    @Id
    private String id;//编号


    @Column(name = "name")
    private String name;//活动名称
    @Column(name = "summary")

    private String summary;//大会简介
    @Column(name = "detail")

    private String detail;//详细说明
    @Column(name = "sponsor")

    private String sponsor;//主办方
    @Column(name = "image")

    private String image;//活动图片
    @Column(name = "startTime")

    private java.util.Date startTime;//开始时间
    @Column(name = "endTime")

    private java.util.Date endTime;//截止时间
    @Column(name = "address")

    private String address;//举办地点
    @Column(name = "enrollTime")

    private java.util.Date enrollTime;//报名截止
    @Column(name = "state")

    private String state;//是否可见
    @Column(name = "city")
    private String city;//城市


}
