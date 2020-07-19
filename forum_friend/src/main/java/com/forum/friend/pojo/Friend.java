package com.forum.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author chenqian091
 * @date 2020-07-19
 */

@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
@Data
public class Friend implements Serializable {


    @Id
    private String userId;

    @Id
    private String friendId;


    private String like;

}
