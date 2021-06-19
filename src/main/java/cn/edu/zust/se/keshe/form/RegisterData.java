package cn.edu.zust.se.keshe.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegisterData {
    private List<RegisterForm> list;
    private int cid;
}
