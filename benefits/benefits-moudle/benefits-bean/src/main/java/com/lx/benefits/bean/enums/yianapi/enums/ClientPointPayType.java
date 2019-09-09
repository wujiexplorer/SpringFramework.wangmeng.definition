package com.lx.benefits.bean.enums.yianapi.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lidongri on 2018/12/2.
 */
public enum ClientPointPayType {

    //普通积分
    general_point(1),
    //专属积分
    exclusive_point(2);

    private int code;

    ClientPointPayType(int code) {
        this.code = code;
    }

    public static int getPayValue(List<String> payCodes) {

        if (payCodes == null || payCodes.isEmpty()) return general_point.getCode()+ exclusive_point.getCode();
        int total = 0;
        for (String s : payCodes) {
            for (ClientPointPayType type : ClientPointPayType.values()) {
                if (type.name().equals(s.trim())) {
                    total = total + type.code;
                }
            }
        }
        return total;
    }

    public static ClientPointPayType getByName(String name) {
        if (name == null) return null;

        for (ClientPointPayType type : ClientPointPayType.values()) {
            if (type.name().equals(name.trim())) return type;
        }
        return null;
    }

    public static String getPayName(Integer i){
      return   Arrays.stream(ClientPointPayType.values()).filter(e-> (e.getCode()&i)==e.getCode()).map(e->e.name()).collect(Collectors.toList()).toString();

    }

    public int getCode() {
        return code;
    }
}
