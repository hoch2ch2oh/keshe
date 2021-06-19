package cn.edu.zust.se.keshe.service;

import cn.edu.zust.se.keshe.dto.ContestDto;
import cn.edu.zust.se.keshe.entity.ContestEntity;
import cn.edu.zust.se.keshe.form.RegisterForm;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContestService {

    public String createContest(ContestDto contestDto);

    public Page<ContestEntity> getContestList(int pageNum, int pageSize);
    //public List<ContestDto> findContestByName(String name);

    public ContestDto findById(int id);

    public String updateContest(ContestDto contestDto);

    public List<RegisterForm> getRegisterForm(int id);

    public String updateRegisterForm(List<RegisterForm> register);

    Page<ContestEntity> searchContest( int type, String keyword, int pageNo, int pageSize);

    Page<ContestEntity> listContestByTime(int status, int pageNo, int pageSize);
}
