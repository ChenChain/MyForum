package com.chain.forum.service;

import com.chain.forum.dao.SpitDao;
import com.chain.forum.pojo.Spit;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/24
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;


    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }


    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishTime(new Date());
        //浏览量
        spit.setVisits(0);
        //分享数
        spit.setShare(0);
        spit.setThumbUp(0);
        //回复数
        spit.setComment(0);
        //状态
        spit.setState("1");

        if (spit.getParentId()!=null&&!"".equals(spit.getParentId())){
            Query query=new Query();
            Update update=new Update();

            query.addCriteria(Criteria.where("_id").is(spit.getParentId()));
           update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }

        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentId(parentId, pageable);
    }

    /**
     * 点赞吐槽
     *
     * @param spitId
     */
    public void thumbup(String spitId) {
        //点一次赞两次数据库查询
//          Spit spit=  spitDao.findById(spitId).get();
//          spit.setThumbUp(spit.getThumbUp()==null?1:spit.getThumbUp()+1);
//          spitDao.save(spit);

        //使用mongo命令自增 db.table.update({},{})

        org.springframework.data.mongodb.core.query.Query query=new Query();
        Update update=new Update();

        query.addCriteria(Criteria.where("_id").is(spitId));
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
