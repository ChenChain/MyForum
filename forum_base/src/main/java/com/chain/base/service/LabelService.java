package com.chain.base.service;

import com.chain.base.dao.LabelDao;
import com.chain.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author chain
 * @date 2020/5/16
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    public void  update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {

     return    labelDao.findAll(new Specification<Label>() {
         /**
          *
          * @param root 根对象
          * @param criteriaQuery 封装查询关键字order  by等 基本不用
          * @param criteriaBuilder 封装对象
          * @return
          */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> list=new ArrayList<>();

                if (label.getLabelName()!=null&&!"".equals(label.getLabelName())){
                    //相当于 where labelname like %name%
                    Predicate p=   criteriaBuilder.like(root.get("labelName").as(String.class), "%"+label.getLabelName()+"%");
                    list.add(p);
                }

                if (label.getState()!=null&&!"".equals(label.getState())){
                    //相当于 where state = 1
                    criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                }

                Predicate [] pre=new Predicate[list.size()];
                pre=list.toArray(pre);
                //相当于 where ... and ...
                return criteriaBuilder.and(pre);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装一个分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll((Specification<Label>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list=new ArrayList<>();
            if (label.getLabelName()!=null&&!"".equals(label.getLabelName())){

                Predicate p=   criteriaBuilder.like(root.get("labelName").as(String.class), "%"+label.getLabelName()+"%");
                list.add(p);
            }
            if (label.getState()!=null&&!"".equals(label.getState())){
                //相当于 where state = 1
                criteriaBuilder.like(root.get("state").as(String.class), label.getState());
            }
            Predicate [] pre=new Predicate[list.size()];
            pre=list.toArray(pre);
            return criteriaBuilder.and(pre);
        },pageable);
    }
}
