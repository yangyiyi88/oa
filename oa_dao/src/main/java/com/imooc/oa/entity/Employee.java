package com.imooc.oa.entity;

public class Employee {
    /*+---------------+-------------+------+-----+---------+-------+
| Field         | Type        | Null | Key | Default | Extra |
+---------------+-------------+------+-----+---------+-------+
| sn            | char(5)     | NO   | PRI | NULL    |       |
| password      | varchar(20) | YES  |     | NULL    |       |
| name          | varchar(20) | YES  |     | NULL    |       |
| department_sn | char(5)     | YES  | MUL | NULL    |       |
| post          | varchar(20) | YES  |     | NULL    |       |
+---------------+-------------+------+-----+---------+-------+
    * */
    private String sn;
    private String password;
    private String name;
    private String departmentSn;
    private String post;
    private Department department = new Department();

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentSn() {
        return departmentSn;
    }

    public void setDepartmentSn(String departmentSn) {
        this.departmentSn = departmentSn;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "sn='" + sn + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", departmentSn='" + departmentSn + '\'' +
                ", post='" + post + '\'' +
                ", department=" + department +
                '}';
    }
}
