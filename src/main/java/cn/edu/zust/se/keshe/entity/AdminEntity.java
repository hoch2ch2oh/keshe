package cn.edu.zust.se.keshe.entity;

/**
 * @author zy 2021/5/24
 */
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "admin", schema = "keshe", catalog = "")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String password;
    @Column
    private String department;
    @Column
    private String phone;

    @Override
    public String toString() {
        return "AdminEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
