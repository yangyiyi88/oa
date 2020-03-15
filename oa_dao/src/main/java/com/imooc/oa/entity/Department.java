package com.imooc.oa.entity;

public class Department {
    /*
 Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| sn      | char(5)      | NO   | PRI | NULL    |       |
| name    | varchar(20)  | YES  |     | NULL    |       |
| address | varchar(100) | YES  |     | NULL
    * */
    private String sn;
    private String name;
    private String address;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Department{" +
                "sn='" + sn + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
