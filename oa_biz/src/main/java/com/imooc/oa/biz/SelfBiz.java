package com.imooc.oa.biz;

import com.imooc.oa.entity.Employee;

public interface SelfBiz {
    Employee login(String sn, String password);
    void changePassword(Employee employee);
}
