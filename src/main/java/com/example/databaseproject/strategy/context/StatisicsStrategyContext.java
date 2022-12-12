package com.example.databaseproject.strategy.context;

import com.example.databaseproject.strategy.Strategy.StatisticsStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisicsStrategyContext {
    @Autowired
    Map<String, StatisticsStrategy> statisticsStrategyMap;

    public List getStatistic(String strategy){
        return statisticsStrategyMap.get(strategy).getStatistics();
    }
}
