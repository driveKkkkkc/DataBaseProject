package com.example.databaseproject.strategy.Strategy;

import com.example.databaseproject.entity.PersonList;
import com.example.databaseproject.mapper.PersonListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatisticsStrategy {
      List<PersonList> getStatistics();
}
