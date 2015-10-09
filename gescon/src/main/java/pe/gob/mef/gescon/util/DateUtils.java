/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author JJacobo
 */
public class DateUtils {

    public static long getDifferenceYears(Date d1, Date d2) {
        final Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        final Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int diff = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return diff;
    }
    
    public static long getDifferenceMonths(Date d1, Date d2) {
        final Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        final Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int diff = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return diff;
    }
    
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    public static long getDifferenceHours(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    public static long getDifferenceMinutes(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
    }
}
