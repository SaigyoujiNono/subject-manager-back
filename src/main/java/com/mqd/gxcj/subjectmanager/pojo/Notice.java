package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_notice")
@ApiModel(value = "Notice对象", description = "")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("发送用户，外键到用户表")
    private String uId;

    @ApiModelProperty("发送用户姓名")
    private String uName;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("发送内容")
    private String content;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;


}
