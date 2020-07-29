package com.chain.article.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * @author Administrator
 *
 */
@Data
@Entity
@Table(name="tb_column")
public class Column implements Serializable{

	@Id
	private String id;//ID


	@javax.persistence.Column(name = "name")
	private String name;//专栏名称
	@javax.persistence.Column(name = "summary")

	private String summary;//专栏简介
	@javax.persistence.Column(name = "user_id")

	private String userId;//用户ID
	@javax.persistence.Column(name = "create_time")

	private Date createTime;//申请日期
	@javax.persistence.Column(name = "check_time")

	private Date checkTime;//审核日期
	@javax.persistence.Column(name = "state")

	private String state;//状态


	
}
