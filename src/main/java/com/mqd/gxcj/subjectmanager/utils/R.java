package com.mqd.gxcj.subjectmanager.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class R {

    //响应码
    private Integer code;

    //消息
    private String msg;

    //数据体
    private Map<String,Object> data = new HashMap<>();

    private R() {}

    public R put(String key,Object value){
        data.put(key,value);
        return this;
    }

    public static R newInstance(RStatus status){
        return new R().setCode(status.code).setMsg(status.msg);
    }

    /**
     * 请求成功
     */
    public static R ok(){
        return new R().setCode(RStatus.OK.code)
                .setMsg(RStatus.OK.msg);
    }

    /**
     * 请求失败
     */
    public static R fail(){
        return new R().setCode(RStatus.ERROR.code)
                .setMsg(RStatus.ERROR.msg);
    }

    /**
     * 登录失败
     */
    public static R loginFailed(){
        return new R().setCode(RStatus.LOGIN_FAILED.code)
                .setMsg(RStatus.LOGIN_FAILED.msg);
    }

    /**
     * 未登录
     */
    public static R notLogin() {
        return new R().setCode(RStatus.NOT_LOGIN.code).
                setMsg(RStatus.NOT_LOGIN.msg);
    }
}
