package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.dao.StatisticDao;
import com.dgut.ssm.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticDao statisticDao;
    public List<String> GetXGoodsType() {
        return statisticDao.GetXGoodsType();
    }

    public List<Integer> getYGoodsType() {
        return statisticDao.getYGoodsType();
    }
}
