package com.chain.user.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Data
@Entity
@Table(name="tb_user")
public class User implements Serializable{

	@Id
	private String id;//ID
	@Column(name = "mobile")
	private String mobile;//手机号码
	@Column(name = "password")
	private String password;//密码
	@Column(name = "nickname")
	private String nickname;//昵称
	@Column(name = "sex")
	private String sex;//性别
	@Column(name = "birthday")
	private java.util.Date birthday;//出生年月日
	@Column(name = "avatar")
	private String avatar;//头像
	@Column(name = "email")
	private String email;//E-Mail
	@Column(name = "reg_date")
	private java.util.Date regdate;//注册日期
	@Column(name = "update_date")
	private java.util.Date updatedate;//修改日期
	@Column(name = "last_date")
	private java.util.Date lastdate;//最后登陆日期
	@Column(name = "online")
	private Long online;//在线时长（分钟）
	@Column(name = "interest")
	private String interest;//兴趣
	@Column(name = "personality")
	private String personality;//个性
	@Column(name = "fan_count")
	private Integer fanscount;//粉丝数
	@Column(name = "follow_count")
	private Integer followcount;//关注数
}
