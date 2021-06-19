package cn.edu.zust.se.keshe.dao;

import cn.edu.zust.se.keshe.entity.T2TEntity;
import cn.edu.zust.se.keshe.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface T2TDao extends JpaRepository<T2TEntity, Integer> {
    List<T2TEntity> findByTid(int id);
}
