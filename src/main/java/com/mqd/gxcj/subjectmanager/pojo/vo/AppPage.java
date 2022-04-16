package com.mqd.gxcj.subjectmanager.pojo.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AppPage {
    private Long current;

    @Max(20)
    @Min(10)
    private Long size;

    private Long total;

    public Long getCurrent() {
        if (this.current == null) return 1L;
        return this.current;
    }

    public Long getSize() {
        if (this.size == null) return 10L;
        return this.size;
    }
}
