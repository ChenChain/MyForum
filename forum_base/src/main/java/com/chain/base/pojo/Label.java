package com.chain.base.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/16
 */
@Data
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {

    @Id
    private String id;

    /**
     * 标签名词
     */
    @Column(name = "label_name")
    private String labelName;

    /**
     * 状体
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 关注数
     */
    private Long fans;

    /**
     * 是否推荐
     */
    private String recommend;

}
