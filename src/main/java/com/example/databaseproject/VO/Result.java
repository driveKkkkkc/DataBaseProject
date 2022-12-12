package com.example.databaseproject.VO;

import com.example.databaseproject.Enum.Renum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    /**
     * 正常响应,无返回数据
     */
    public Result() {
        Renum renum = Renum.SUCCESS;
        this.message = renum.getMessage();
        this.code = renum.getCode();
        this.success = renum.getSuccess();
    }

    /**
     * 正常响应，加返回数据
     *
     * @param data 数据
     */
    public Result(T data) {
        Renum renum = Renum.SUCCESS;
        this.message = renum.getMessage();
        this.code = renum.getCode();
        this.success = renum.getSuccess();
        this.data = data;
    }
    /**
     * 正常响应，加返回数据和指定语句
     *
     * @param data 数据
     */
    public Result(T data,String message) {
        Renum renum = Renum.SUCCESS;
        this.message = renum.getMessage();
        this.code = renum.getCode();
        this.success = renum.getSuccess();
        this.data = data;
    }
    /**
     * 统一异常处理
     *
     * @param renum 枚举类
     */
    public Result(T data, Renum renum) {
        this.message = renum.getMessage();
        this.code = renum.getCode();
        this.success = renum.getSuccess();
        this.data = data;
    }

    /**
     * 特定异常处理
     *
     * @param message
     */
    public Result(T data, Integer code, String message) {
        this.message = message;
        this.code = code;
        this.data = data;
        this.success = false;
    }


}