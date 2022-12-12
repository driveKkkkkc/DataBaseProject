package com.example.databaseproject.strategy.ConcreteStrategy;

import com.example.databaseproject.entity.PersonList;
import com.example.databaseproject.mapper.PersonListMapper;
import com.example.databaseproject.strategy.Strategy.RankStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeRankAction implements RankStrategy {
    @Autowired
    PersonListMapper mapper;
    //按年龄从大到小排序
    @Override
    public List<PersonList> getRankList(){
        return mapper.selectPersonListRankByAge();
    }
}
