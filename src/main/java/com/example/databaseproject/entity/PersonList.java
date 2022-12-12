package com.example.databaseproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.beans.DefaultProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kcc
 * @since 2022-12-05
 */
@Data
@TableName("person_list")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PersonList对象", description = "")
public class PersonList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("p_no")
    private String pNo;

    @TableField("name")
    private String name = "";

    @TableField("sex")
    private String sex = "";

    @TableField("age")
    private Integer age = 0;

    @TableField("title")
    private String title = "";

    @TableField("politic")
    private String politic = "";

    @TableField("education")
    private String education = "";

    @TableField("duration")
    private LocalDate duration;

    @TableField("enter_time")
    private LocalDate enterTime;

    @ApiModelProperty("在职人员类别")
    @TableField("p_type")
    private String pType = "";

    @ApiModelProperty("退休")
    @TableField("retired")
    private String retired = "否";

    @ApiModelProperty("临时工")
    @TableField("temp")
    private String temp = "否";


}
