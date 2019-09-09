package com.lx.benefits.service.account;

import com.lx.benefits.bean.base.dto.PageResultBean;
import com.lx.benefits.bean.vo.account.AccountBankReq;
import com.lx.benefits.bean.vo.account.AccountBankVO;

import java.io.OutputStream;
import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:17:50
 * Version:2.x
 * Description:TODO
 **/
public interface AccountBankService {

    public PageResultBean<AccountBankVO> getAccountBankDetail(AccountBankReq accountBankReq);

    public void exportAccountBank(AccountBankReq accountBankReq, OutputStream outputStream);

    public List<AccountBankVO> listAccountBankDetail(AccountBankReq accountBankReq);
}
