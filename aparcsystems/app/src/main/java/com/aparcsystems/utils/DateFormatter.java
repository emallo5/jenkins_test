package com.aparcsystems.utils;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Emiliano on 27/12/2014.
 */
public class DateFormatter {
    public static Date parseApiDate(String string){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date= null;
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            Log.e("DateParseError","Error parsing date: "+string);
        }
        return date;
    }

    public static String getDateToShow(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }

    public static String getTimeToShow(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static String getMonthFromInteger(int monthNumber){
        Calendar date=Calendar.getInstance();
        date.set(Calendar.MONTH,monthNumber);
        return  new SimpleDateFormat("MMM").format(date.getTime());
    }
}
