package com.chain.article.pojo;

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
@Table(name="tb_channel")
public class Channel implements Serializable{

	@Id
	private String id;//ID
	@Column(name = "name")
	private String name;//频道名称
	@Column(name = "state")
	private String state;//状态
}
