package cn.edu.zust.se.keshe.entity;

/**
 * @author zy 2021/5/24
 */
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "contest", schema = "keshe", catalog = "")

public class ContestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String sponsor;
    @Column(name = "number_limit")
    private int numberLimit;
    @Column(name = "register_start_time")
    private Date registerStartTime;
    @Column(name = "register_end_time")
    private Date registerEndTime;
    @Column(name = "contest_start_time")
    private Date contestStartTime;
    @Column(name = "contest_end_time")
    private Date contestEndTime;
    @Column
    private String description;

    @Override
    public String toString() {
        return "ContestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", number_limit=" + numberLimit +
                ", register_start_time=" + registerStartTime +
                ", register_end_time=" + registerEndTime +
                ", contest_start_time=" + contestStartTime +
                ", contest_end_time=" + contestEndTime +
                ", description='" + description + '\'' +
                '}';
    }
}
