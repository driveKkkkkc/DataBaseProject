package com.example.databaseproject.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.databaseproject.entity.PersonList;
import com.example.databaseproject.mapper.PersonListMapper;
import com.example.databaseproject.service.PersonListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.databaseproject.strategy.Strategy.RankStrategy;
import com.example.databaseproject.strategy.Strategy.StatisticsStrategy;
import com.example.databaseproject.strategy.context.RankStrategyContext;
import com.example.databaseproject.strategy.context.StatisicsStrategyContext;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kcc
 * @since 2022-12-05
 */
@Service
public class PersonListServiceImpl extends ServiceImpl<PersonListMapper, PersonList> implements PersonListService {

    @Autowired
    PersonListMapper mapper;

    @Autowired
    RankStrategyContext rankStrategyContext;

    @Autowired
    StatisicsStrategyContext statisicsStrategyContext;

    //编号查询
    @Override
    public List<PersonList> getPersonByNo(String pNo){
        return mapper.selectPersonListByPno(pNo);
    }
    //姓名查询
    @Override
    public List<PersonList> getPersonByName(String name){
        return mapper.selectPersonListByName(name);
    }

    //分页条件查询人员数据
    @Override
    public List getPagePersonList(String rankAction,String statisticAction){
        //采用排序策略调用sql
        if (rankAction!=null){
            return rankStrategyContext.getRank(rankAction);
        }
        //采用统计策略调用sql
        if (statisticAction!=null){
            return statisicsStrategyContext.getStatistic(statisticAction);
        }
        //不采用任何策略
        return mapper.selectPersonListAll();
    }

    //更新人员信息
    @Override
    public boolean updatePerson(PersonList personList){
        return mapper.updateAll(personList.getPNo(),personList.getName(),personList.getSex(),personList.getAge(),personList.getTitle(),
                personList.getPolitic(),personList.getEducation(),personList.getDuration(),personList.getEnterTime(),personList.getPType(),
                personList.getRetired(),personList.getTemp());
    }

    @Override
    public Boolean deletePerson(String pNo){
        return mapper.deletePerson(pNo);
    }

    @Override
    public Boolean insertPerson(PersonList personList){
        return mapper.insertPerson(personList.getPNo(),personList.getName(),personList.getSex(),personList.getAge(),personList.getTitle(),
                personList.getPolitic(),personList.getEducation(),personList.getDuration(),personList.getEnterTime(),personList.getPType(),
                personList.getRetired(),personList.getTemp());
    }
}
