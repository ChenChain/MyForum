package com.forum.friend.pojo;

import lombok.Data;

import javax.persistence.*;
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
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "friend_id")
    private String friendId;

    @Column(name = "like")
    private String like;

}
