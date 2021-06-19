package cn.edu.zust.se.keshe.form;

import cn.edu.zust.se.keshe.dto.TeacherDto;
import cn.edu.zust.se.keshe.entity.StudentEntity;
import cn.edu.zust.se.keshe.entity.TeacherEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterForm {
    //竞赛id
    private int cid;
    //团队id
    private int tid;
    //团队名称
    private String name;
    //团队成员
    private List<StudentEntity> students;
    //联系方式
    private String phone;
    //指导老师
    private TeacherEntity teacher;
    //成绩
    private String score;
}
