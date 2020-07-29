package com.chain.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chain.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

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
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problem_id and label_id=? order by reply_time desc ")
    public Page<Problem> newlist(String labelid, Pageable pageable);

    /**
     * 热门
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problem_id and label_id=? order by reply desc ")
    public Page<Problem> hotlist(String labelid, Pageable pageable);

    /**
     * 待回答问题
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * from tb_problem ,tb_pl WHERE id=problem_id and label_id=?  and  reply=0 order by create_time desc ")
    public Page<Problem> waitlist(String labelid, Pageable pageable);

}
