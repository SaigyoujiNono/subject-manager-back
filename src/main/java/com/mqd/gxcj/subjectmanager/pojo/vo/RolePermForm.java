package com.mqd.gxcj.subjectmanager.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RolePermForm {
    @NotNull
    private Integer roleId;
    @NotEmpty
    private List<Integer> permIds;
}
