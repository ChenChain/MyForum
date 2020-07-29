package com.chain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chain.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    User findByMobile(String mobile);


    @Modifying
    @Query(value="update tb_user set fan_count=fan_count +?  where id =?",nativeQuery=true)
    void updateFanCount(int x, String friendId);

    @Modifying
    @Query(value="update tb_user set follow_count=follow_count +?  where id =?",nativeQuery=true)
    void updateFollowCount(int x, String userId);
}
