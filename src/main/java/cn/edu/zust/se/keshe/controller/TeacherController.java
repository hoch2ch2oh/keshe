package cn.edu.zust.se.keshe.controller;

import cn.edu.zust.se.keshe.dto.TeacherDto;
import cn.edu.zust.se.keshe.entity.StudentEntity;
import cn.edu.zust.se.keshe.entity.TeacherEntity;
import cn.edu.zust.se.keshe.form.TeacherForm;
import cn.edu.zust.se.keshe.form.UploadForm;
import cn.edu.zust.se.keshe.service.TeacherService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.*;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @GetMapping("/admin/teacherList")
    public String teacherList(Model model, @RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo){
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        // TODO: 2021/1/18 其实是要验证是否为数字
        Page<TeacherEntity> teachers = teacherService.listTeacher(Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", teachers.getTotalPages());
        model.addAttribute("teachers",teachers);
        return "/admin/teacherList";
    }

    @GetMapping("/admin/teacherInfo")
    public String toUpdateTeacher(int id, Model model){
        TeacherDto teacher = teacherService.findTeacherById(id);
        TeacherForm t = new TeacherForm();
        t.setId(teacher.getId());
        t.setPassword(teacher.getPassword());
        t.setTrueName(teacher.getTrueName());
        t.setGender(teacher.getGender());
        t.setDepartment(teacher.getDepartment());
        t.setProfessionalTitle(teacher.getProfessionalTitle());
        t.setPhone(teacher.getPhone());
        model.addAttribute("t",t);
        model.addAttribute("id",id);
        return "/admin/teacherInfo";
    }

    @PostMapping("/admin/updateTeacher")
    public String updateTeacher(@ModelAttribute TeacherForm t, HttpSession session, int id, RedirectAttributes redirectAttributes){
        System.out.println(t.toString());
        if(!t.getPassword().equals(t.getConfirmPassword())){
            session.setAttribute("error","两次密码不一致");
            return "error";
        }
        redirectAttributes.addAttribute("id",id);
        TeacherDto teacher = teacherService.findTeacherById(id);
        if(t.getConfirmPassword()!=null&&t.getConfirmPassword().length()!=0){
            teacher.setPassword(t.getPassword());
            String res = teacherService.updateTeacher(teacher);
            System.out.println("i got here!");
            return "redirect:/admin/teacherInfo";
        }
        teacher.setDepartment(t.getDepartment());
        teacher.setProfessionalTitle(t.getProfessionalTitle());
        String res = teacherService.updateTeacher(teacher);
        if(!res.equals("success")){
            session.setAttribute("error",res);
            return "error";
        }
        return "redirect:/admin/teacherInfo";
        // TODO: 2021/6/17
    }



    @PostMapping("/admin/uploadTeacher")
    public String upload(MultipartHttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        for (Map.Entry<String, MultipartFile> part : fileMap.entrySet()) {
            InputStream inputStream = part.getValue().getInputStream();
            Map<Integer, String> head = new HashMap<>();
            List<Map<Integer, String>> data = new LinkedList<>();
            EasyExcel.read(inputStream).sheet()
                    .registerReadListener(new AnalysisEventListener<Map<Integer, String>>() {

                        @Override
                        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                            head.putAll(headMap);
                            System.out.println(head);
                        }

                        @Override
                        public void invoke(Map<Integer, String> row, AnalysisContext analysisContext) {
                            data.add(row);
                        }

                        @Override
                        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                            //log.info("读取文件[{}]成功,一共:{}行......", part.getKey(), data.size());
                            //System.out.println("输出data:\n");
                            List<TeacherDto> teachers = new ArrayList<>();
                            List<UploadForm> uploadForms = new ArrayList<>();
                            for(Map<Integer, String> row:data){
                                TeacherDto t = new TeacherDto();
                                UploadForm uploadForm = new UploadForm();

                                uploadForm.setType("教师");
                                uploadForm.setName(row.get(0));
                                uploadForm.setDepartment(row.get(2));
                                uploadForm.setMajor(row.get(3));

                                // TODO: 2021/6/16 需要一个md5的加密
                                t.setPassword("123456");
                                t.setTrueName(row.get(0));
                                t.setGender(row.get(1));
                                t.setDepartment(row.get(2));
                                t.setProfessionalTitle(row.get(3));
                                teachers.add(t);
                                uploadForms.add(uploadForm);
                            }
                            teacherService.saveTeachers(teachers);
                            redirectAttributes.addAttribute("uploads",uploadForms);
                        }
                    }).doRead();
            // 其他业务逻辑
        }
        return "redirect:/admin/uploadList";
    }


    @RequestMapping("/admin/deleteTeacher")
    public String deleteTeacher(int id){
        teacherService.delete(id);
        return "redirect:/admin/teacherList";
    }

    @PostMapping("/admin/searchTeacher")
    public String searchStudent(@RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo, String keyword, String type, Model model){
        System.out.println(keyword+" "+type);
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        int t = Integer.valueOf(type);
        //int s = Integer.valueOf(status);
        Page<TeacherEntity> teachers = teacherService.searchContest(t,keyword,Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", teachers.getTotalPages());
        model.addAttribute("teachers",teachers);
        return "/admin/teacherList";
    }

}
