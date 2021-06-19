package cn.edu.zust.se.keshe.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author zy 2021/5/24
 */
@Data
@ApiModel("学生用户")
public class StudentDto {
    @ApiModelProperty("学生ID")
    private int id;
    @ApiModelProperty("用户密码")
    private String password;
    @ApiModelProperty("真实姓名")
    private String trueName;
    @ApiModelProperty("性别")
    private String gender;
    @ApiModelProperty("所属学院")
    private String department;
    @ApiModelProperty("专业")
    private String major;
    @ApiModelProperty("班级")
    private String clazz;
    @ApiModelProperty("手机号")
    private String phone;

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
