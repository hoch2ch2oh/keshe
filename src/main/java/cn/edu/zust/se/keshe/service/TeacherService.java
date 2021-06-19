package cn.edu.zust.se.keshe.service;

import cn.edu.zust.se.keshe.dto.TeacherDto;
import cn.edu.zust.se.keshe.entity.TeacherEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    public String saveTeachers(List<TeacherDto> teachers);

    public String updateTeacher(TeacherDto teacher);

    public Page<TeacherEntity> listTeacher(int pageNo, int pageSize);

    public TeacherDto findTeacherById(int id);

    public void delete(int id);

    Page<TeacherEntity> searchContest(int type, String keyword, int pageNo, int pageSize);
}
