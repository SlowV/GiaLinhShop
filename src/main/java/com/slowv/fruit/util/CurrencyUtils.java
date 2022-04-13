package com.slowv.fruit.util;

import lombok.var;

import java.text.DecimalFormat;

public class CurrencyUtils {
    public static String format(double money) {
        var formatter = new DecimalFormat("###,###,###");
        return formatter.format(money) + " VNĐ";
    }
}
