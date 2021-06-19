package cn.edu.zust.se.keshe.dto;

/**
 * @author zy 2021/5/24
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.*;

@Data
@ApiModel("管理员用户")
public class AdminDto {
    @ApiModelProperty("管理员ID")
    private Integer id;
    @ApiModelProperty("管理员密码")
    private String password;
    @ApiModelProperty("所属学院")
    private String department;
    @ApiModelProperty("手机号")
    private String phone;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
