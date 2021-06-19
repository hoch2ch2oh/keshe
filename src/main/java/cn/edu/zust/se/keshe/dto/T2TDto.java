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
@ApiModel("教师收到的组队申请")
public class T2TDto {
    @ApiModelProperty("申请ID")
    private int id;
    @ApiModelProperty("团队ID")
    private int tid;
    @ApiModelProperty("教师ID")
    private int teacherId;


}
