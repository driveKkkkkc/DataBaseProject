package com.example.databaseproject.service;

import com.example.databaseproject.entity.PersonList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kcc
 * @since 2022-12-05
 */
public interface PersonListService extends IService<PersonList> {

    //编号查询
    List<PersonList> getPersonByNo(String pNo);

    //姓名查询
    List<PersonList> getPersonByName(String name);

    //分页条件查询人员数据
    List getPagePersonList(String rankAction, String statisticAction);

    //更新人员信息
    boolean updatePerson(PersonList personList);

    Boolean deletePerson(String pNo);

    Boolean insertPerson(PersonList personList);
}
