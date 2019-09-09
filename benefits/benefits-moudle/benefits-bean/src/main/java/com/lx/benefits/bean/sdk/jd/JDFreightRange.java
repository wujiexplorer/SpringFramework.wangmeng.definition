package com.lx.benefits.bean.sdk.jd;

/**
 *
 * 京东 邮费
 * Created by lidongri on 2018/4/26.
 */
public enum JDFreightRange {


    F_1(0D,88D,8D),

    F_2(88D,128D,6D),

    F_3(128D,Double.MAX_VALUE,0D);

    /**
     * 总价区间 from 包含
     */
    private double from;

    /**
     * 总价区间 to 不包含
     */
    private double to;

    /**
     * 运费
     */
    private double freight;

    public static final double free = 128D;


    JDFreightRange(double from, double to , double freight){
        this.from = from;
        this.to = to;
        this.freight = freight;
    }


    public static double calFreight(Double totalPrice){
        if(totalPrice == null || totalPrice<=0D){
            totalPrice = 0D;
        }
        for(JDFreightRange r: JDFreightRange.values()){
            if(r.from<= totalPrice && totalPrice<r.to) return r.freight;
        }

        return F_1.freight;

    }



}
