package cn.edu.zust.se.keshe.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * @author zy 2021/5/24
 */

@Setter
@Getter
@Entity
@Table(name = "teacher", schema = "keshe", catalog = "")
public class TeacherEntity {
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
    @Column(name = "professional_title")
    private String professionalTitle;
    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
