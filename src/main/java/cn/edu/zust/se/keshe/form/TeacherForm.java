package cn.edu.zust.se.keshe.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherForm {
    private int id;
    private String password;
    private String confirmPassword;
    private String trueName;
    private String gender;
    private String department;
    private String professionalTitle;
    private String phone;
}
