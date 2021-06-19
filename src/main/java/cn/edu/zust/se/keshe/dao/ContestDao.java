package cn.edu.zust.se.keshe.dao;

import cn.edu.zust.se.keshe.entity.ContestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zy 2021/5/24
 */
public interface ContestDao extends JpaRepository<ContestEntity, Integer> {
    List<ContestEntity> findByName(String name);

    Page<ContestEntity> findByNameLike(String s, Pageable pageable);

    Page<ContestEntity> findBySponsorLike(String s, Pageable pageable);

    Page<ContestEntity> findAll(Specification specification, Pageable pageable);
}
