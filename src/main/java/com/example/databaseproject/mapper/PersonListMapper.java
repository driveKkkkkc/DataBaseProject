package com.example.databaseproject.mapper;

import com.example.databaseproject.entity.PersonList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kcc
 * @since 2022-12-05
 */
@Mapper
public interface PersonListMapper extends BaseMapper<PersonList> {

    //查询所有数据
    @Select("SELECT * FROM person_list;")
    List<PersonList> selectPersonListAll();

    //根据编号查询个人
    @Select("SELECT * FROM person_list as p WHERE p.p_no LIKE '%${pNo}%'; ")
    List<PersonList> selectPersonListByPno(@Param("pNo") String pNo);

    //按年龄从大到小排序
    @Select("SELECT * FROM person_list as p ORDER BY(p.age) DESC;")
    List<PersonList> selectPersonListRankByAge();

    //按来院时间从晚到早排序
    @Select("SELECT * FROM person_list as p ORDER BY(p.enter_time) DESC;")
    List<PersonList> selectPersonListRankByEnterDate();

    //根据姓名查询个人
    @Select("SELECT * FROM person_list as p WHERE p.name LIKE '%${name}%'; ")
    List<PersonList> selectPersonListByName(@Param("name") String name);

    //根据编号更新数据
    @Update("UPDATE person_list as p " +
            "SET p.name = #{name},p.sex = #{sex},p.age = #{age},p.title=#{title}," +
            "p.politic=#{politic},p.education=#{education}, " +
            "p.duration=#{duration},p.enter_time=#{enter_time}, " +
            "p.p_type=#{p_type},p.retired=#{retired},p.temp=#{temp} " +
            "WHERE p.p_no = #{pno}")
    Boolean updateAll(@Param("pno") String pno,@Param("name") String name,@Param("sex") String sex,@Param("age") Integer age,
                      @Param("title") String title,@Param("politic") String politic,
                      @Param("education") String education,@Param("duration") LocalDate duration,
                      @Param("enter_time") LocalDate enterTime,@Param("p_type") String pType,
                      @Param("retired") String retired,@Param("temp") String temp);

    //根据编号删除数据
    @Delete("DELETE FROM person_list as p WHERE p.p_no = #{pno}; ")
    Boolean deletePerson(@Param("pno") String pNo);

    //新增数据
    @Insert("INSERT INTO person_list " +
            "VALUES(#{pno},#{name},#{sex},#{age},#{title},#{politic},#{education},#{duration},#{enter_time},#{p_type},#{retired},#{temp});")
    Boolean insertPerson(@Param("pno") String pNo,@Param("name") String name,@Param("sex") String sex,@Param("age") Integer age,
                         @Param("title") String title,@Param("politic") String politic,
                         @Param("education") String education,@Param("duration") LocalDate duration,
                         @Param("enter_time") LocalDate enterTime,@Param("p_type") String pType,
                         @Param("retired") String retired,@Param("temp") String temp);

    /**
     * 统计功能
     */
    //在职人员
    @Select("SELECT * FROM person_list as p WHERE p.retired = '否' AND p.temp = '否';")
    List<PersonList> selectOnWorkingList();

    //党员人数
    @Select("SELECT * FROM person_list as p WHERE p.politic = '党员';")
    List<PersonList> selectPartyMemberList();

    //女工人数
    @Select("SELECT * FROM person_list as p WHERE p.sex = '女';")
    List<PersonList> selectWomenList();

    //高学历高职称人数
    @Select("SELECT * FROM person_list as p WHERE p.title IN('副教授','教授','院士') OR p.education IN ('硕士','博士','博士后');")
    List<PersonList> selectEliteList();
}
