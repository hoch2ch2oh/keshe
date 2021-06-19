package cn.edu.zust.se.keshe.service;

import cn.edu.zust.se.keshe.dto.StudentDto;
import cn.edu.zust.se.keshe.entity.ContestEntity;
import cn.edu.zust.se.keshe.entity.StudentEntity;
import cn.edu.zust.se.keshe.form.RegisterData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    
    public String saveStudents(List<StudentDto> students);

    public String updateStudent(StudentDto stu);

    public Page<StudentEntity> listStudent(int pageNo, int pageSize);

    public StudentDto findStudentById(int id);

    public void delete(int id);

    public Page<StudentEntity> searchContest(int type, String keyword, int pageNo, int pageSize);

    // TODO: 2021/6/16  查找学生 
}
