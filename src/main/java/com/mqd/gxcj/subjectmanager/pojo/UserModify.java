package com.mqd.gxcj.subjectmanager.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户修改个人信息所需要的中间表
 * </p>
 *
 * @author 莫桥德
 * @since 2022-03-22
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("s_user_modify")
@ApiModel(value = "UserModify对象", description = "用户修改个人信息所需要的中间表")
public class UserModify implements Serializable {
    public static final String COMMITTED = "committed";
    public static final String CHECKED = "checked";
    public static final String NO_CHECKED = "no-checked";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("审核人id")
    private String checker;

    @ApiModelProperty("审核状态，committed已提交，checked审核通过，no-checked审核未通过")
    private String status;

    @ApiModelProperty("审核时间")
    private LocalDateTime checkedTime;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("提交修改的用户id")
    private String userId;

    @ApiModelProperty("需要修改的原因")
    private String reason;

    @ApiModelProperty("修改后的名字")
    private String name;

    @ApiModelProperty("修改后的学历信息")
    private Integer education;

    @ApiModelProperty("修改后的毕业学校")
    private String graduateInstitution;

    @ApiModelProperty("修改后的毕业专业")
    private String specialization;

    @ApiModelProperty("修改后的毕业时间")
    private LocalDateTime graduateDate;

    @ApiModelProperty("修改后的职务")
    private Integer duty;

    @ApiModelProperty("修改后的职称")
    private Integer rank;

    @ApiModelProperty("修改后的生日")
    private LocalDateTime birth;

    @ApiModelProperty("修改后的国家")
    private String country;

    @ApiModelProperty("修改后的籍贯")
    private String origo;

    @ApiModelProperty("修改后的住址")
    private String address;

    @ApiModelProperty("修改后的家庭电话")
    private String homeTelephone;

    @ApiModelProperty("修改后的办公电话")
    private String officeTelephone;

    @ApiModelProperty("修改后的移动电话")
    private String mobile1;

    @ApiModelProperty("修改后的备用电话")
    private String mobile2;

    @ApiModelProperty("修改后的学科id")
    private Integer subjectId;


}
