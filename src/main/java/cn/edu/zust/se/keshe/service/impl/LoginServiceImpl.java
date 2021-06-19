package cn.edu.zust.se.keshe.service.impl;

import cn.edu.zust.se.keshe.dao.StudentDao;
import cn.edu.zust.se.keshe.dao.TeacherDao;
import cn.edu.zust.se.keshe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    @Override
    public boolean login(String loginName, String password, int t) {
        if(t==1){
            if(studentDao.findByTrueNameAndPassword(loginName,password)==null)  return false;
            return true;
        }
        if(t==1){
            if(teacherDao.findByTrueNameAndPassword(loginName,password)==null)  return false;
            return true;
        }
        return false;
    }
}
