package cn.edu.zust.se.keshe.controller;

import cn.edu.zust.se.keshe.dto.ContestDto;
import cn.edu.zust.se.keshe.entity.ContestEntity;
import cn.edu.zust.se.keshe.form.RegisterData;
import cn.edu.zust.se.keshe.form.RegisterForm;
import cn.edu.zust.se.keshe.service.ContestService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
public class ContestController {
    @Autowired
    ContestService contestService;

    @GetMapping("/error")
    public String error(){
        return "/error";
    }

    @GetMapping("/admin/createContest")
    public String toCreate(Model model){
        model.addAttribute("contest",new ContestDto());
        return "/admin/createContest";
    }

    /****
        报名开始时间不得早于当前时间
        表单不能有空
        比赛名称不能重复
     */
    @PostMapping("/admin/createContest")
    public String createContest(@ModelAttribute ContestDto contest, Model model, HttpSession session){
        if (contest.getName()==null||contest.getName().length()==0){
            session.setAttribute("error","报名开始时间不得早于当前时间");
            return "redirect:error";
        }
        Date now = new Date();
        if(contest.getRegisterStartTime().getTime()-now.getTime()<0){
            session.setAttribute("error","报名开始时间不得早于当前时间");
            return "redirect:error";
        }
        if(contest.getRegisterEndTime().getTime()-contest.getRegisterStartTime().getTime()<1000*60*60){
            session.setAttribute("error","报名开始的一个小时之后，才可结束报名");
            return "redirect:error";
        }
        if(contest.getContestStartTime().getTime()-contest.getRegisterEndTime().getTime()<1000*60*60){
            session.setAttribute("error","报名结束的一个小时之后，方可进行比赛");
            return "redirect:error";
        }
        if(contest.getContestEndTime().getTime()-contest.getContestStartTime().getTime()<1000*60*60){
            session.setAttribute("error","比赛开始的一个小时之后，才可结束比赛");
            return "redirect:error";
        }
        String res = contestService.createContest(contest);
        if(!res.equals("success")){
            session.setAttribute("error",res);
            return "redirect:error";
        }
        return "redirect:/admin/contestList";
    }

    @GetMapping("/admin/contestList")
    public String contestList(Model model,@RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo){
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        // TODO: 2021/1/18 其实是要验证是否为数字
        Page<ContestEntity> contests = contestService.getContestList(Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", contests.getTotalPages());
        model.addAttribute("contests",contests);
        return "/admin/contestList";
    }

    @PostMapping("/admin/searchContest")
    public String searchContest(@RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo, String keyword, String type, Model model){
        System.out.println(keyword+" "+type);
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        int t = Integer.valueOf(type);
        //int s = Integer.valueOf(status);
        Page<ContestEntity> contests = contestService.searchContest(t,keyword,Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", contests.getTotalPages());
        model.addAttribute("contests",contests);
        return "/admin/contestList";
    }

    @RequestMapping("/admin/listContestByTime")
    public String listContestByTime(@RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo, String status, Model model){
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        int s = Integer.valueOf(status);
        if(s==3)    return "redirect:/admin/contestList";
        Page<ContestEntity> contests = contestService.listContestByTime(s,Integer.valueOf(pageNo)-1,8);
        int page = 1;
        if (contests!=null)
            page = contests.getTotalPages();
        model.addAttribute("totalPage", page);
        model.addAttribute("contests",contests);
        return "/admin/contestList";
    }

    @RequestMapping("/admin/toUpdate")
    public String toUpdateContest(Model model,int id){
        ContestDto contest = contestService.findById(id);
        model.addAttribute("contest",contest);
        //System.out.println(contest.toString());
        return "/admin/updateContest";
    }

    /***
     *只有对时间的判断
     */
    @PostMapping("/admin/contestUpdate")
    public String updateContest(@ModelAttribute(value="contest") ContestDto contest, int id, HttpSession session){
        contest.setId(id);
        System.out.println(contest.toString());
        if(contest.getRegisterEndTime().getTime()-contest.getRegisterStartTime().getTime()<1000*60*60){
            session.setAttribute("error","报名开始的一个小时之后，才可结束报名");
            return "redirect:error";
        }
        if(contest.getContestStartTime().getTime()-contest.getRegisterEndTime().getTime()<1000*60*60){
            session.setAttribute("error","报名结束的一个小时之后，方可进行比赛");
            return "redirect:error";
        }
        if(contest.getContestEndTime().getTime()-contest.getContestStartTime().getTime()<1000*60*60){
            session.setAttribute("error","比赛开始的一个小时之后，才可结束比赛");
            return "redirect:error";
        }
        String res = contestService.updateContest(contest);
        if(!res.equals("success")){
            session.setAttribute("error",res);
            return "redirect:error";
        }
        return "redirect:/admin/contestList";
    }

    @GetMapping("/admin/toScoreList")
    public String toScoreList(Model model,int id){
        List<RegisterForm> registerForm = contestService.getRegisterForm(id);
        RegisterData rfs = new RegisterData();
        rfs.setList(registerForm);
        rfs.setCid(id);
        model.addAttribute("registers",rfs);
        model.addAttribute("id",id);
        return "/admin/scoreList";
    }

    @PostMapping("/admin/scoreList")
    public String updateScoreList(@ModelAttribute(value = "registers") RegisterData rfs, int id, RedirectAttributes redirectAttributes){
        //假设id已经在rfs里，且为hidden
        System.out.println(rfs.getList().toString());
        String result =  contestService.updateRegisterForm(rfs.getList());
        redirectAttributes.addAttribute("id",id);
        return "redirect:/admin/toScoreList";
    }


    @GetMapping("/admin/exportContests")
    public void exportExcel(HttpServletResponse response, int id, String name) throws Exception {
        // 这里文件名如果涉及中文一定要使用URL编码,否则会乱码
        String fileName = URLEncoder.encode(name+"人员名单.xlsx", StandardCharsets.UTF_8.toString());
        // 封装标题行
        List<List<String>> head = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("团队id");
        List<String> head1 = new ArrayList<>();
        head1.add("团队名称");
        List<String> head2 = new ArrayList<>();
        head2.add("负责人");
        List<String> head3 = new ArrayList<>();
        head3.add("联系方式");
        List<String> head4 = new ArrayList<>();
        head4.add("指导老师");
        List<String> head5 = new ArrayList<>();
        head5.add("竞赛成绩");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        head.add(head5);

        List<RegisterForm> rfs = contestService.getRegisterForm(id);

        // 封装数据
        List<List<Object>> datas = new LinkedList<>();
        for(RegisterForm rf:rfs){
            List<Object> data = new ArrayList<>();
            data.add(rf.getTid());
            data.add(rf.getName());
            data.add(rf.getStudents().get(0).getTrueName());
            data.add(rf.getStudents().get(0).getPhone());
            data.add(rf.getTeacher().getTrueName());
            // TODO: 2021/6/16 值为空的时候，会不会报错，或者加一个判断，如果为null，则变成“”
            data.add(rf.getScore());
            datas.add(data);
        }

        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        EasyExcel.write(response.getOutputStream())
                .head(head)
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("Sheet1")
                .doWrite(datas);


    }


}
