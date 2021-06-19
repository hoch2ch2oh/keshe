package cn.edu.zust.se.keshe.dao;

import cn.edu.zust.se.keshe.entity.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zy 2021/5/24
 */
public interface TeacherDao extends JpaRepository<TeacherEntity, Integer> {

    Page<TeacherEntity> findByTrueNameLike(String s, Pageable pageable);

    Page<TeacherEntity> findByDepartmentLike(String s, Pageable pageable);

    Page<TeacherEntity> findByProfessionalTitleLike(String s, Pageable pageable);

    TeacherEntity findByTrueNameAndPassword(String loginName, String password);
}
