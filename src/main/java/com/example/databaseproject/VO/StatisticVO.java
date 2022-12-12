package com.example.databaseproject.VO;

import com.example.databaseproject.entity.PersonList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticVO {
    Integer sum;
    List<PersonList> personList;
}
