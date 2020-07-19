package com.forum.friend.dao;

import com.forum.friend.pojo.Friend;
import com.forum.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chenqian091
 * @date 2020-07-19
 */
public interface NoFriendDao extends JpaRepository<NoFriendDao, String> {

     NoFriend findByUserIdAndAndFriendId(String userId, String friendId);


}
