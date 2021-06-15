package com.wjf.elasticsearch.demo.mapper;

import com.wjf.elasticsearch.demo.entity.NewCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author KingPlus
 * @date 2021年06月14日  23:08
 */
@Repository
@Mapper
public interface NewCarMapper {

    public List<NewCar> findAll();
 }
