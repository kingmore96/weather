package com.ging.weather.enums;

public enum ResultEnum {
    PARAM_ID_ERROR(1,"天气id不能为空"),
    PARAM_NAME_ERROR(2,"天气名称不能为空"),
    DATA_ERROR(100,"获取天气数据异常"),
    UNKNOWN_ERROR(999,"未知异常");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
