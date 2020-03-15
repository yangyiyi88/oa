package com.imooc.oa.controller;

import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.biz.SelfBiz;
import com.imooc.oa.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller("SelfController")
public class SelfController {

    @Resource(name = "selfBizImpl")
    private SelfBiz selfBiz;
    @Resource(name = "employeeBizImpl")
    private EmployeeBiz employeeBiz;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, @RequestParam("sn") String sn, @RequestParam("password") String password){
        Employee employee = selfBiz.login(sn, password);
        if(employee != null){
            session.setAttribute("employee",employee);
            return "redirect:self";
        }
        return "redirect:to_login";
    }

    @RequestMapping("/to_change_password")
    public String toChangePassword(){
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam("old") String old, @RequestParam("new1")String new1, @RequestParam("new2")String new2){
        Employee employee = (Employee)session.getAttribute("employee");
        if(employee.getPassword().equals(old) && new1.equals(new2)){
            employee.setPassword(new1);
            selfBiz.changePassword(employee);
            return "redirect:to_login";
        }
        return "change_password";
    }

    @RequestMapping("/self")
    public String self(HttpSession session){
        return "self";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session){
        session.setAttribute("employee",null);
        return "redirect:to_login";
    }
}
