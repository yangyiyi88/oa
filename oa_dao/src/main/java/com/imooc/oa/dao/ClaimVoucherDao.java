package com.imooc.oa.dao;

import com.imooc.oa.entity.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherDao")
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);
    void update(ClaimVoucher claimVoucher);
    void delete(Integer id);
    ClaimVoucher select(Integer id);
    List<ClaimVoucher> selectByCreateSn(String sn);
    List<ClaimVoucher> selectByNextDealSn(String sn);
}
