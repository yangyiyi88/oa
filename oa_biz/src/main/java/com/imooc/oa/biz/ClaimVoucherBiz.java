package com.imooc.oa.biz;

import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.ClaimVoucherItem;
import com.imooc.oa.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherBiz {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(Integer id);
    List<ClaimVoucherItem> getItems(Integer cvid);
    List<DealRecord> getRecords(Integer cvid);

    List<ClaimVoucher> getForSelf(String sn);

    List<ClaimVoucher> getForDeal(String  sn);

    void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    void submit(Integer id);

    void deal(DealRecord dealRecord);

}
