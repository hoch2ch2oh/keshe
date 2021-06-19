package cn.edu.zust.se.keshe.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentForm {
    private int id;
    private String password;
    private String confirmPassword;
    private String trueName;
    private String gender;
    private String department;
    private String major;
    private String clazz;
    private String phone;

    @Override
    public String toString() {
        return "StudentForm{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", trueName='" + trueName + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
