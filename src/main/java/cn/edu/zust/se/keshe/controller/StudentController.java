package cn.edu.zust.se.keshe.controller;

import cn.edu.zust.se.keshe.dto.StudentDto;
import cn.edu.zust.se.keshe.entity.StudentEntity;
import cn.edu.zust.se.keshe.form.StudentForm;
import cn.edu.zust.se.keshe.form.UploadForm;
import cn.edu.zust.se.keshe.service.StudentService;
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
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/admin/studentList")
    public String studentList(Model model, @RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo){
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        // TODO: 2021/1/18 其实是要验证是否为数字
        Page<StudentEntity> students = studentService.listStudent(Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", students.getTotalPages());
        model.addAttribute("students",students);
        return "/admin/studentList";
    }


    @GetMapping("/admin/studentInfo")
    public String toUpdateStudent(int id, Model model){
        StudentDto student = studentService.findStudentById(id);
        StudentForm stu = new StudentForm();
        stu.setId(student.getId());
        stu.setPassword(student.getPassword());
        stu.setTrueName(student.getTrueName());
        stu.setGender(student.getGender());
        stu.setDepartment(student.getDepartment());
        stu.setMajor(student.getMajor());
        stu.setClazz(student.getClazz());
        stu.setPhone(student.getPhone());
        model.addAttribute("stu",stu);
        model.addAttribute("id",id);
        return "/admin/studentInfo";
    }
    
    @PostMapping("/admin/updateStudent")
    public String updateStudent(@ModelAttribute StudentForm stu, HttpSession session, int id, RedirectAttributes redirectAttributes){
        System.out.println(stu.toString());
        if(!stu.getPassword().equals(stu.getConfirmPassword())){
            session.setAttribute("error","两次密码不一致");
            return "error";
        }
        redirectAttributes.addAttribute("id",id);
        StudentDto student = studentService.findStudentById(id);
        if(stu.getConfirmPassword()!=null&&stu.getConfirmPassword().length()!=0){
            student.setPassword(stu.getPassword());
            String res = studentService.updateStudent(student);
            System.out.println("i got here!");
            return "redirect:/admin/studentInfo";
        }
        student.setDepartment(stu.getDepartment());
        student.setMajor(stu.getMajor());
        student.setClazz(stu.getClazz());
        String res = studentService.updateStudent(student);
        if(!res.equals("success")){
            session.setAttribute("error",res);
            return "error";
        }
        return "redirect:/admin/studentInfo";
        // TODO: 2021/6/17
    }


    @PostMapping("/admin/uploadStudent")
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
                            List<StudentDto> students = new ArrayList<>();
                            List<UploadForm> uploadForms = new ArrayList<>();
                            for(Map<Integer, String> row:data){
                                StudentDto stu = new StudentDto();
                                UploadForm uploadForm = new UploadForm();

                                uploadForm.setType("学生");
                                uploadForm.setName(row.get(0));
                                uploadForm.setDepartment(row.get(2));
                                uploadForm.setMajor(row.get(3));

                                // TODO: 2021/6/16 需要一个md5的加密
                                stu.setPassword("123456");
                                stu.setTrueName(row.get(0));
                                stu.setGender(row.get(1));
                                stu.setDepartment(row.get(2));
                                stu.setMajor(row.get(3));
                                stu.setClazz(row.get(4));
                                students.add(stu);
                                uploadForms.add(uploadForm);
                            }
                            studentService.saveStudents(students);
                            redirectAttributes.addAttribute("uploads",uploadForms);
                        }
                    }).doRead();
            // 其他业务逻辑
        }
        return "redirect:/admin/uploadList";
    }

    @RequestMapping("/admin/uploadList")
    public String uploadList(@ModelAttribute(value = "uploads") List<UploadForm> uploads, Model model){
        model.addAttribute("uploads",uploads);
        return "/admin/uploadList";
    }

    @RequestMapping("/admin/deleteStudent")
    public String deleteStudent(int id){
        studentService.delete(id);
        return "redirect:/admin/studentList";
    }

    @PostMapping("/admin/searchStudent")
    public String searchStudent(@RequestParam(defaultValue = "1", required = true, value = "PageNo") String pageNo, String keyword, String type, Model model){
        System.out.println(keyword+" "+type);
        if(pageNo==null||pageNo.length()==0)  pageNo = "1";
        int t = Integer.valueOf(type);
        //int s = Integer.valueOf(status);
        Page<StudentEntity> students = studentService.searchContest(t,keyword,Integer.valueOf(pageNo)-1,8);
        model.addAttribute("totalPage", students.getTotalPages());
        model.addAttribute("students",students);
        return "/admin/studentList";
    }

}
