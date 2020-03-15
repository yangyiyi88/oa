package com.imooc.oa.controller;

import com.imooc.oa.biz.ClaimVoucherBiz;
import com.imooc.oa.dao.ClaimVoucherDao;
import com.imooc.oa.dto.ClaimVoucherInfo;
import com.imooc.oa.entity.ClaimVoucher;
import com.imooc.oa.entity.DealRecord;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.global.Contant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("ClaimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

    @Resource(name = "claimVoucherBizImpl")
    private ClaimVoucherBiz claimVoucherBiz;

    @RequestMapping("to_add")
    public String toAdd(Map<String,Object> map){
        map.put("items", Contant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @RequestMapping("add")
    public String add(HttpSession session, ClaimVoucherInfo info){
        Employee  employee = (Employee)session.getAttribute("employee");
        ClaimVoucher claimVoucher = info.getClaimVoucher();
        claimVoucher.setCreateSn(employee.getSn());

        claimVoucherBiz.save(claimVoucher,info.getItems());
        return "redirect:self";
    }

    @RequestMapping("detail")
    public String detail(Map<String,Object> map, Integer id){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session, Map<String,Object> map){
        Employee employee = (Employee)session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String,Object> map){
        Employee employee = (Employee)session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_edit")
    public String toEdit(Map<String,Object> map, Integer id){
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setItems(claimVoucherBiz.getItems(id));
        info.setClaimVoucher(claimVoucherBiz.get(id));
        map.put("info", info);
        map.put("items", Contant.getItems());
        return "claim_voucher_edit";
    }

    @RequestMapping("edit")
    public String edit(HttpSession session, ClaimVoucherInfo info){
        Employee employee = (Employee)session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.edit(info.getClaimVoucher(), info.getItems());
        return "redirect:self";
    }

    @RequestMapping("/submit")
    public String submit(Integer id){
        claimVoucherBiz.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("to_check")
    public String toCheck(Map<String,Object> map, Integer id){
        map.put("claimVoucher",claimVoucherBiz.get(id));
        map.put("items",claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("dealRecord",dealRecord);
        return "claim_voucher_check";
    }

    @RequestMapping("check")
    public String check(HttpSession session, DealRecord dealRecord){
        Employee employee = (Employee)session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);
        return "redirect:deal";
    }
}
