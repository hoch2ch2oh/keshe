package cn.edu.zust.se.keshe.dao;

import cn.edu.zust.se.keshe.entity.S2TEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface S2TDao extends JpaRepository<S2TEntity, Integer> {
    List<S2TEntity> findByTid(int id);
}
