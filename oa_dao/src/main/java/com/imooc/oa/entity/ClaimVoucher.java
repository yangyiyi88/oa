package com.imooc.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ClaimVoucher {
    /*+--------------+--------------+------+-----+---------+----------------+
| Field        | Type         | Null | Key | Default | Extra          |
+--------------+--------------+------+-----+---------+----------------+
| id           | int(11)      | NO   | PRI | NULL    | auto_increment |
| cause        | varchar(100) | YES  |     | NULL    |                |
| create_sn    | char(5)      | YES  | MUL | NULL    |                |
| create_time  | datetime     | YES  |     | NULL    |                |
| next_deal_sn | char(5)      | YES  | MUL | NULL    |                |
| total_amount | double       | YES  |     | NULL    |                |
| status       | varchar(20)  | YES  |     | NULL    |                |
+--------------+--------------+------+-----+---------+----------------+
    * */
    private Integer id;
    private String cause;
    private String createSn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String nextDealSn;
    private Double totalAmount;
    private String status;

    private Employee creater;
    private Employee dealter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCreateSn() {
        return createSn;
    }

    public void setCreateSn(String createSn) {
        this.createSn = createSn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealSn() {
        return nextDealSn;
    }

    public void setNextDealSn(String nextDealSn) {
        this.nextDealSn = nextDealSn;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getCreater() {
        return creater;
    }

    public void setCreater(Employee creater) {
        this.creater = creater;
    }

    public Employee getDealter() {
        return dealter;
    }

    public void setDealter(Employee dealter) {
        this.dealter = dealter;
    }
}
