package com.forum.friend.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 非关系好友 点击不喜欢
 * @author chenqian091
 * @date 2020-07-19
 */

@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
@Data
public class NoFriend implements Serializable {


    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "friend_id")
    private String friendId;

}
