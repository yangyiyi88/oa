package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.ClaimVoucherBiz;
import com.imooc.oa.dao.ClaimVoucherDao;
import com.imooc.oa.dao.ClaimVoucherItemDao;
import com.imooc.oa.dao.DealRecordDao;
import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.ClaimVoucherItem;
import com.imooc.oa.entity.DealRecord;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Contant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("claimVoucherBizImpl")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Resource(name = "claimVoucherDao")
    private ClaimVoucherDao claimVoucherDao;
    @Resource(name = "claimVoucherItemDao")
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Resource(name = "dealRecordDao")
    private DealRecordDao dealRecordDao;
    @Resource(name = "employeeDao")
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.insert(claimVoucher);

        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(claimVoucher.getId());
        dealRecord.setComment("无");
        dealRecord.setDealSn(claimVoucher.getCreateSn());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealWay(Contant.DEAL_CREATE);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_CREATED);
        dealRecordDao.insert(dealRecord);
    }

    public ClaimVoucher get(Integer id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(Integer cvid) {
        return claimVoucherItemDao.selectByClaimVoucherId(cvid);
    }

    public List<DealRecord> getRecords(Integer cvid) {
        return dealRecordDao.selectByClaimVoucherId(cvid);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    public void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucherDao.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucherId(claimVoucher.getId());
        for (ClaimVoucherItem old : olds) {
            Boolean have = false;
            for (ClaimVoucherItem item : items) {
                System.out.println("oldid" + old.getId() + "\titem id:" + item.getId() + "equals" + old.getId().equals(item.getId()));
                if (old.getId().equals(item.getId())) {
                    have = true;
                    break;
                }
            }
            if (!have) {
                claimVoucherItemDao.delete(old.getId());
            }
        }

        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            if (item.getId() != null) {
                claimVoucherItemDao.update(item);
            } else {
                claimVoucherItemDao.insert(item);
            }
        }

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(claimVoucher.getId());
        dealRecord.setComment("无");
        dealRecord.setDealSn(claimVoucher.getCreateSn());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealWay(Contant.DEAL_UPDATE);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_CREATED);
        dealRecordDao.insert(dealRecord);

    }

    public void submit(Integer id) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.POST_FM).get(0).getSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claimVoucherDao.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(claimVoucher.getId());
        dealRecord.setComment("无");
        dealRecord.setDealSn(claimVoucher.getCreateSn());
        dealRecord.setDealTime(new Date());
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecordDao.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        dealRecord.setDealTime(new Date());
        if (dealRecord.getDealWay().equals(Contant.DEAL_PASS)) {
            if (claimVoucher.getTotalAmount() <= Contant.LIMIT_CHECK || employeeDao.select(claimVoucher.getNextDealSn()).getPost().equals(Contant.POST_GM)) {
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_CASHIER).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            } else {
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            }
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_BACK)) {
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_REJECT)) {
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_PAID)) {
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }

        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }
}
