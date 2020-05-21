package com.chain.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chain.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 最新问题回答
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problemid and labelid=? order by replytime desc ")
    public Page<Problem> newlist(String labelid, Pageable pageable);

    /**
     * 热门
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problemid and labelid=? order by reply desc ")
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    /**
     * 待回答问题
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problemid and labelid=?  and  reply=0 order by createtime desc ")
    public Page<Problem> waitlist(String labelid, Pageable pageable);

}
