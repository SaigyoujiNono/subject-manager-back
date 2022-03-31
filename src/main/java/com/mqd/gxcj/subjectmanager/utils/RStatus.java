package com.mqd.gxcj.subjectmanager.utils;

public enum RStatus {
    OK(20000,"ok"),
    LOGIN_FAILED(40003,"账号或者密码错误"),
    ACCOUNT_NOT_EXIST(40005,"用户不存在"),
    ACCOUNT_EXIST(40006, "账号已存在"),
    PROJECT_EXIST(40007, "项目已存在"),
    VERIFY_ERROR(40013,"参数校验失败"),
    FILE_NOT_EXIST(40014,"文件不存在"),
    NOT_LOGIN(40002,"未登录"),
    NOT_PERMISSION(40003,"权限不足"),
    ERROR(40001,"error");

    public final int code;
    public final String msg;
    RStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}