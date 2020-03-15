package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Contant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {

    @Resource(name = "departmentBizImpl")
    private DepartmentBiz departmentBiz;
    @Resource(name = "employeeBizImpl")
    private EmployeeBiz employeeBiz;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("elist", employeeBiz.getAll());
        return "employee_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("employee", new Employee());
        map.put("departments", departmentBiz.getAll());
        map.put("posts", Contant.getPosts());
        return "employee_add";
    }

    @RequestMapping("/add")
    public String add(Map<String,Object> map,Employee employee){
        employeeBiz.add(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_edit", params = "sn")
    public String toEdit(Map<String,Object> map, String sn){
        map.put("employee", employeeBiz.get(sn));
        map.put("departments", departmentBiz.getAll());
        map.put("posts", Contant.getPosts());
        return "employee_edit";
    }

    @RequestMapping("/edit")
    public String edit(Map<String,Object> map,Employee employee){
        employeeBiz.edit(employee);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(Map<String,Object> map, String sn){
        employeeBiz.remove(sn);
        return "redirect:list";
    }
}
