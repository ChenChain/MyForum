package com.chain.forum.dao;

import com.chain.forum.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/24
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据父id查找子id
     * @param parentId
     * @return
     */
    Page<Spit> findByParentId(String parentId, Pageable pageable);
}
