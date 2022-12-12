package com.example.databaseproject.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.databaseproject.VO.Result;
import com.example.databaseproject.VO.StatisticVO;
import com.example.databaseproject.entity.PersonList;
import com.example.databaseproject.service.PersonListService;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kcc
 * @since 2022-12-05
 */
@ApiModel("接口文档")
@RestController
@RequestMapping("/Person")
public class PersonListController {

    @Autowired
    PersonListService service;

    @PostMapping("/getPageList")
    public Result getPageListByCondition(@RequestBody JSONObject request){
        Integer pageSize = request.getInt("pageSize");
        Integer pageNum = request.getInt("pageNum");
        String rankAction = request.getStr("rankAction",null);
        String statisticAction = request.getStr("statisticAction",null);
        List<PersonList> personList = service.getPagePersonList(rankAction,statisticAction);
        StatisticVO result = new StatisticVO(personList.size(),pageBySubList(personList,pageSize,pageNum));
        return new Result(result);
    }

    @PostMapping("/getPerson")
    public Result getPersonByNameOrNo(@RequestBody JSONObject request){
        String keyWord = request.getStr("keyWord","");
        Integer keyWordType = request.getInt("keyWordType",1);
        List<PersonList> result = null;
        if (keyWordType == 1){
            result = service.getPersonByName(keyWord);
        }

        if (keyWordType == 2){
            result = service.getPersonByNo(keyWord);
        }

        return new Result(result);
    }

    @PostMapping("/deletePerson")
    public Result deletePersonByPno(@RequestBody JSONObject request){
        String pNo = request.getStr("pNo");
        Boolean result = service.deletePerson(pNo);
        return new Result(result);
    }

    @PostMapping("/updatePerson")
    public Result updatePersonByPNO(@RequestBody PersonList personList){
        System.err.println(personList);
        Boolean result = service.updatePerson(personList);
        return new Result(result);
    }

    @PostMapping("/insertPerson")
    public Result insertPerson(@RequestBody PersonList personList){
        System.err.println(personList);
        Boolean result = service.insertPerson(personList);
        return new Result(result);
    }
    //对列表进行分页
    public List pageBySubList(List list, int pageSize, int currentPage) {
        if (pageSize == 0||list.size()==0) return list;

        int totalCount = list.size();
        int pageCount = 0;
        List<?> subList;
        int m = totalCount % pageSize;
        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }

        if(currentPage>pageCount) return list;

        if (m == 0) {
            subList = list.subList((currentPage - 1) * pageSize, pageSize * (currentPage));
        } else {
            if (currentPage == pageCount) {
                subList = list.subList((currentPage - 1) * pageSize, totalCount);
            } else {
                subList = list.subList((currentPage - 1) * pageSize, pageSize * (currentPage));
            }
        }
        return subList;
    }
}
