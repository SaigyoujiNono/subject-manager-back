package com.mqd.gxcj.subjectmanager.pojo.route;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Route {
    private Integer id;
    private String path;
    private String component;
    private String name;
    private Meta meta;
    private List<Route> children;
    private Integer parent;
    private Boolean hidden;
    private String title;
}
