package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {

    @Resource(name = "departmentBizImpl")
    private DepartmentBiz departmentBiz;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("dlist", departmentBiz.getAll());
        return "department_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("department", new Department());
        return "department_add";
    }

    @RequestMapping("/add")
    public String add(Map<String,Object> map,Department department){
        departmentBiz.add(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_edit", params = "sn")
    public String toEdit(Map<String,Object> map, String sn){
        map.put("department", departmentBiz.get(sn));
        return "department_edit";
    }

    @RequestMapping("/edit")
    public String edit(Map<String,Object> map,Department department){
        departmentBiz.edit(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(Map<String,Object> map, String sn){
        departmentBiz.remove(sn);
        return "redirect:list";
    }
}
