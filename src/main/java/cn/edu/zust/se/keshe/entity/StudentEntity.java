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
@Table(name = "student", schema = "keshe", catalog = "")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String password;
    @Column(name = "true_name")
    private String trueName;
    @Column
    private String gender;
    @Column
    private String department;
    @Column
    private String major;
//    @Column(name = "class")
    private String clazz;
    @Column
    private String phone;

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
