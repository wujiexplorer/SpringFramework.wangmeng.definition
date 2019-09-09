package com.lx.benefits.mapper.account;

import com.lx.benefits.bean.vo.account.AccountBankReq;
import com.lx.benefits.bean.vo.account.AccountBankVO;

import java.util.List;

/**
 * User:wangmeng
 * Date:2019/7/15
 * Time:15:46
 * Version:2.x
 * Description:TODO
 **/
public interface AccountBankMapper {

    public List<AccountBankVO> getAccountBankDetail(AccountBankReq accountBankReq);

    public Integer countAccountBank(AccountBankReq accountBankReq);

    public List<AccountBankVO> listAccountBankDetail(AccountBankReq accountBankReq);
}
