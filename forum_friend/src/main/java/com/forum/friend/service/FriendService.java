package com.forum.friend.service;

import com.forum.friend.dao.FriendDao;
import com.forum.friend.dao.NoFriendDao;
import com.forum.friend.pojo.Friend;
import com.forum.friend.pojo.NoFriend;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author chenqian091
 * @date 2020-07-14
 */
@Transactional
@Service
public class FriendService {


    @Resource
    private FriendDao friendDao;

    @Resource
    private NoFriendDao noFriendDao;

    public int addFriend(String userId,String friendId){
        //type 0单向喜欢
        //1 双向喜欢
        Friend friend=   friendDao.findByUserIdAndAndFriendId(userId, friendId);
        if (friend!=null){
            return 0;
        }
        //单向喜欢
        friend=new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setLike("0");
        friendDao.save(friend);

        //friend-->user 方向是否喜欢
        if ( friendDao.findByUserIdAndAndFriendId(friendId,userId)!=null){
            //双方喜欢like 都改1
            friendDao.updateLike("1",userId,friendId);
            friendDao.updateLike("1",friendId,userId);
        }
        return 1;
    }



    public int addNoFriend(String userId,String friendId){
        NoFriend noFriend= noFriendDao.findByUserIdAndAndFriendId(userId, friendId);
        if (noFriend!=null){
            return 0;
        }
        noFriend=new NoFriend();
        noFriend.setFriendId(friendId);
        noFriend.setUserId(userId);
        noFriendDao.save(noFriendDao);
        return 1;
    }

}
