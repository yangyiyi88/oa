package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.dao.DepartmentDao;
import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("employeeBizImpl")
public class EmployeeBizImpl implements EmployeeBiz {

    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
