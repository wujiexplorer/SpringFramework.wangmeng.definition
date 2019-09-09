package com.lx.benefits.web.controller;

import com.apollo.starter.web.utils.Result;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;
import com.lx.benefits.bean.vo.cart.CartVO;
import com.lx.benefits.bean.param.cart.CartSaveParam;
import com.lx.benefits.support.cart.CartSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车
 **/
@RestController
@RequestMapping("/enterprise/cart")
public class CartController {

    @Resource
    private CartSupport cartSupport;


	@PostMapping("/")
	public Result<CartVO> index() {
		SessionShopInfo sessionEmployeeInfo = SessionContextHolder.getSessionEmployeeInfo();
		return Result.wrapDefaultSuccessResult(cartSupport.list(sessionEmployeeInfo.getEmployeeId(), sessionEmployeeInfo.getEnterprId()));
	}

    @PostMapping("/save")
    public Result<?> add(@RequestBody CartSaveParam cartAddParam){
    	SessionShopInfo employeeInfo = SessionContextHolder.getSessionEmployeeInfo();
        cartSupport.addCart(cartAddParam,employeeInfo.getEmployeeId(),employeeInfo.getEnterprId());
        return Result.wrapDefaultSuccessResult();
    }

    @PostMapping("/mod/checked/{checked}")
    public Result<?> modChecked(@PathVariable Integer checked,@RequestBody List<Long> cartProductIdList){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        cartSupport.modChecked(cartProductIdList,accountId,checked);
        return Result.wrapDefaultSuccessResult();
    }


    @PostMapping("/remove/{flag}")
    public Result<?> remove(@RequestBody List<Long> cartProductIdList,@PathVariable Boolean flag){
        Long accountId = SessionContextHolder.getSessionEmployeeInfo().getEmployeeId();
        cartSupport.removeCart(cartProductIdList,accountId,flag);
        return Result.wrapDefaultSuccessResult();
    }
}