package com.imooc.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DealRecord {
    /*+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| id               | int(11)      | NO   | PRI | NULL    | auto_increment |
| claim_voucher_id | int(11)      | YES  | MUL | NULL    |                |
| deal_sn          | char(5)      | YES  | MUL | NULL    |                |
| deal_time        | datetime     | YES  |     | NULL    |                |
| deal_way         | varchar(20)  | YES  |     | NULL    |                |
| deal_result      | varchar(20)  | YES  |     | NULL    |                |
| comment          | varchar(100) | YES  |     | NULL    |                |
+------------------+--------------+------+-----+---------+----------------+
    * */
    private Integer id;
    private Integer claimVoucherId;
    private String  dealSn;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime;
    private String dealWay;
    private String dealResult;
    private String comment;

    private Employee dealer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClaimVoucherId() {
        return claimVoucherId;
    }

    public void setClaimVoucherId(Integer claimVoucherId) {
        this.claimVoucherId = claimVoucherId;
    }

    public String getDealSn() {
        return dealSn;
    }

    public void setDealSn(String dealSn) {
        this.dealSn = dealSn;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Employee getDealer() {
        return dealer;
    }

    public void setDealer(Employee dealer) {
        this.dealer = dealer;
    }
}
