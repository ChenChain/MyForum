package com.chain.recruit.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类 企业信息
 * @author Administrator
 *
 */
@Entity
@Data
@Table(name="tb_enterprise")
public class Enterprise implements Serializable {

	@Id
	private String id;//ID

	@Column(name = "name")
	private String name;//企业名称
	@Column(name = "summary")
	private String summary;//企业简介
	@Column(name = "address")
	private String address;//企业地址
	@Column(name = "labels")
	private String labels;//标签列表
	@Column(name = "coordinate")
	private String coordinate;//坐标
	@Column(name = "hot")
	private boolean hot;//是否热门
	@Column(name = "logo")
	private String logo;//LOGO
	@Column(name = "job_count")
	private Integer jobCount;//职位数
	@Column(name = "url")
	private String url;//URL

}
