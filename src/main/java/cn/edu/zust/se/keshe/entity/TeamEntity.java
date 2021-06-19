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
@Table(name = "team", schema = "keshe", catalog = "")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column(name = "team_number")
    private int teamNumber;
    @Column
    private String description;
    @Column
    private int cid;
    @Column(name = "s_check")
    private int sCheck;
    @Column(name = "t_check")
    private int tCheck;
    @Column
    private String score;
    //private ContestEntity contestByCid;


    @Override
    public String toString() {
        return "TeamEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamNumber=" + teamNumber +
                ", description='" + description + '\'' +
                ", sCheck=" + sCheck +
                ", tCheck=" + tCheck +
                ", score='" + score + '\'' +
                '}';
    }
}
