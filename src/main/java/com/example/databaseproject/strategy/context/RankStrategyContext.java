package com.example.databaseproject.strategy.context;

import com.example.databaseproject.strategy.Strategy.RankStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RankStrategyContext {
    @Autowired
    private Map<String, RankStrategy> rankStrategyMap;

    public List getRank(String strategy){
        return rankStrategyMap.get(strategy).getRankList();
    }
}
