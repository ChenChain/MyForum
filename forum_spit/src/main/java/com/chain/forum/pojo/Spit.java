package com.chain.forum.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/24
 */
@Data
@ApiModel(value = "吐槽内容 实体对象")
public class Spit  implements Serializable {

    /**
     * mongo中主键
     */
    @ApiModelProperty("主键")
    @Id
    private String _id;

    @ApiModelProperty(value = "吐槽内容",required = false)
    private String content;

    @ApiModelProperty(value = "吐槽日期")
    private Date publishTime;

    @ApiModelProperty(value = "吐槽者id")
    private String userId;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("浏览次数")
    private  Integer visits;

    @ApiModelProperty("点赞次数")
    private Integer thumbUp;

    private Integer share;

    @ApiModelProperty("回复数")
    private Integer comment;

    private String state;

    @ApiModelProperty("父亲节点id")
    private String parentId;




}
