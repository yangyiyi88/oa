<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sping" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.imooc.oa.global.Contant" %>

<jsp:include page="top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 待处理报销单 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel  heading-border">
                <div class="panel-menu">
                    <div class="row">
                        <div class="hidden-xs hidden-sm col-md-3">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-refresh"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-trash"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-plus" onclick="javascript:window.location.href='/claim_voucher/to_add';"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-9 text-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-left"></i>
                                </button>
                                <button type="button" class="btn btn-default light">
                                    <i class="fa fa-chevron-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel-body pn">
                    <table id="message-table" class="table admin-form theme-warning tc-checkbox-1">
                        <thead>
                        <tr class="">
                            <th class="text-center hidden-xs">Select</th>
                            <th class="hidden-xs">事由</th>
                            <th>状态</th>
                            <th class="hidden-xs">创建人</th>
                            <th class="hidden-xs">金额</th>
                            <th class="text-center">创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="claimVoucher">
                        <tr class="message-unread">
                            <td class="hidden-xs">
                                <label class="option block mn">
                                    <input type="checkbox" name="mobileos" value="FR">
                                    <span class="checkbox mn"></span>
                                </label>
                            </td>
                            <td>${claimVoucher.cause}</td>
                            <td class="hidden-xs">
                                <span class="badge badge-warning mr10 fs11"> ${claimVoucher.status}</span>
                            </td>
                            <td>${claimVoucher.creater.name}</td>
                            <td class="text-center fw600">${claimVoucher.totalAmount}</td>
                            <td><sping:eval expression="claimVoucher.createTime"/></td>
                            <c:if test="${claimVoucher.status==Contant.CLAIMVOUCHER_CREATED || claimVoucher.status==Contant.CLAIMVOUCHER_BACK}">
                                <td><a href="/claim_voucher/to_edit?id=${claimVoucher.id}">修改</a></td>
                                <td><a href="/claim_voucher/submit?id=${claimVoucher.id}">提交</a></td>
                            </c:if>
                            <c:if test="${claimVoucher.status==Contant.CLAIMVOUCHER_SUBMIT || claimVoucher.status==Contant.CLAIMVOUCHER_RECHECK}">
                                <td><a href="/claim_voucher/to_check?id=${claimVoucher.id}">审核</a></td>
                            </c:if>
                            <c:if test="${claimVoucher.status==Contant.CLAIMVOUCHER_APPROVED}">
                                <td><a href="/claim_voucher/to_check?id=${claimVoucher.id}">打款</a></td>
                            </c:if>
                            <td><a href="/claim_voucher/detail?id=${claimVoucher.id}">详细信息</a></td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"/>