package com.example.databaseproject.Enum;

import lombok.Getter;

@Getter
public enum Renum {

    /**
     * 成功响应
     */
    SUCCESS(true, 200, "成功"),
    /**
     * 类型为空
     */
    TYPE_NULL_ERROR(false, 401, "类型不能为空"),
    /**
     * 传输过程异常
     */
    NULL_ERROR(false, 402, "服务程序数据传输过程异常，请联系管理员"),
    /**
     * 未知的数据类型
     */
    UNKNOWN_TYPE(false, 403, "类型不识别"),
    /**
     * 运行时异常处理
     */
    RUN_ERROR(false, 404, "服务器运行出错"),
    /**
     * 权限不足
     */
    WITHOUT_AUTHORITY(false, 405, "权限不足");

    /**
     * 响应是否成功
     */
    private final Boolean success;
    /**
     * 响应状态码
     */
    private final Integer code;
    /**
     * 响应信息
     */
    private final String message;


    Renum(Boolean success, int code, String messsage) {
        this.success = success;
        this.code = code;
        this.message = messsage;
    }
}
