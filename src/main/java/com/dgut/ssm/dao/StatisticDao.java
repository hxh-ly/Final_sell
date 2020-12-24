package com.dgut.ssm.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticDao {
    //获取总坐标
    public List<String> GetXGoodsType();
    //获取销售数量
    public List<Integer> getYGoodsType();
}
