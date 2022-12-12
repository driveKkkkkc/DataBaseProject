package com.example.databaseproject.strategy.ConcreteStrategy;

import com.example.databaseproject.entity.PersonList;
import com.example.databaseproject.mapper.PersonListMapper;
import com.example.databaseproject.strategy.Strategy.StatisticsStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WomenStatisticsAction implements StatisticsStrategy {
    @Autowired
    PersonListMapper mapper;

    @Override
    public List<PersonList> getStatistics() {
        return mapper.selectWomenList();
    }
}
