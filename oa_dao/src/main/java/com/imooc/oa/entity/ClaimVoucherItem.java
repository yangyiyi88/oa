package com.imooc.oa.entity;

public class ClaimVoucherItem {
    /*+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| id               | int(11)      | NO   | PRI | NULL    | auto_increment |
| claim_voucher_id | int(11)      | YES  | MUL | NULL    |                |
| item             | varchar(20)  | YES  |     | NULL    |                |
| amount           | double       | YES  |     | NULL    |                |
| comment          | varchar(100) | YES  |     | NULL    |                |
+------------------+--------------+------+-----+---------+----------------+
    * */
    private Integer id;
    private Integer claimVoucherId;
    private String item;
    private Double amount;
    private String comment;

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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ClaimVoucherItem{" +
                "id=" + id +
                ", claimVoucherId=" + claimVoucherId +
                ", item='" + item + '\'' +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }
}