package cn.edu.zust.se.keshe.dao;

import cn.edu.zust.se.keshe.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy 2021/5/24
 */
public interface StudentDao extends JpaRepository<StudentEntity, Integer> {
    Page<StudentEntity> findByTrueNameLike(String s, Pageable pageable);

    Page<StudentEntity> findByDepartmentLike(String s, Pageable pageable);

    Page<StudentEntity> findByMajorLike(String s, Pageable pageable);

    Page<StudentEntity> findByClazzLike(String s, Pageable pageable);

    StudentEntity findByTrueNameAndPassword(String loginName, String password);
}
