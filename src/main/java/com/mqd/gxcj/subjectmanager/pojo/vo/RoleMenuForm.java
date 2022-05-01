package com.mqd.gxcj.subjectmanager.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleMenuForm {
    @NotNull
    private Integer id;
    @NotEmpty
    private List<Integer> menuIds;
}
