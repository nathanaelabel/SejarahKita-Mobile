package com.uc.sejarahkita_mobile.helper;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String getNewDateFormat(String currentFormat, String newFormat, String date) {
        String newDate = date;
        try {
            SimpleDateFormat currentSimpleDateFormat = new SimpleDateFormat(currentFormat);
            Date currentDate = currentSimpleDateFormat.parse(date);

            SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat(newFormat);
            newDate = newSimpleDateFormat.format(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Check Tanggal", "getNewDateFormat: " +e.getMessage());
        }
        return newDate;
    }
}