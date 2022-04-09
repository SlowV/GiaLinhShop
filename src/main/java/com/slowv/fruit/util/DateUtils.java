package com.slowv.fruit.util;

import lombok.var;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static String longToString(long milli) {
        var calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milli);
        var formatter = new SimpleDateFormat("dd/MM/yyyy k:mm:ss");
        return formatter.format(calendar.getTime());
    }
}
