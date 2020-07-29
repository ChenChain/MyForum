package com.forum.friend.dao;

import com.forum.friend.pojo.Friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chenqian091
 * @date 2020-07-19
 */
public interface FriendDao extends JpaRepository<Friend, String> {

     Friend findByUserIdAndAndFriendId(String userId, String friendId);


    @Modifying
    @Query(value= "UPDATE  ta_friend set islike =? where user_id=?  and friend_id=? " , nativeQuery=true)
     void updateLike(String like, String userId, String friendId);

    @Modifying
    @Query(value= "delete from ta_friend  where user_id=?  and friend_id=? " , nativeQuery=true)
    void deleteFriend(String userid, String friendId);
}
