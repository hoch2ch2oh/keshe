package cn.edu.zust.se.keshe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zy 2021/5/24
 */
@Setter
@Getter
@Entity
@Table(name = "t2t", schema = "keshe", catalog = "")
public class T2TEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int tid;
    @Column(name = "teacher_id")
    private int teacherId;


}
