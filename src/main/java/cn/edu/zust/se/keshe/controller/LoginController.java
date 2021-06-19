package cn.edu.zust.se.keshe.controller;


import cn.edu.zust.se.keshe.dao.StudentDao;
import cn.edu.zust.se.keshe.dto.StudentDto;
import cn.edu.zust.se.keshe.service.LoginService;
import cn.edu.zust.se.keshe.service.StudentService;
import cn.edu.zust.se.keshe.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public String toLogin(){
        return "login";
    }

    @PostMapping("login")
    public String login(String loginName, String password, String type, HttpSession httpSession){
        int t = Integer.valueOf(type);
        if(t==0){
            if(loginName.equals("1001")&&password.equals("123456")) return "/admin/home";
            else{
                httpSession.setAttribute("error","不存在该用户，或密码不匹配，登陆失败");
                return "error";
            }
        }
        if(t==1){
            if(loginService.login(loginName, password, t)) return "/student/home";
            else{
                httpSession.setAttribute("error","不存在该用户，或密码不匹配，登陆失败");
                return "error";
            }
        }
        if(t==2){
            if(loginService.login(loginName, password, t)) return "/teacher/home";
            else{
                httpSession.setAttribute("error","不存在该用户，或密码不匹配，登陆失败");
                return "error";
            }
        }
        return "error";
    }
}
