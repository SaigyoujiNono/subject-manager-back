package com.mqd.gxcj.subjectmanager.exception;

import com.mqd.gxcj.subjectmanager.utils.RStatus;
import lombok.Getter;
import lombok.Setter;

public class AppException extends Exception{
    @Setter
    @Getter
    private RStatus status;

    public AppException() {
    }
    public AppException(RStatus status){
        this.status = status;
    }

}
