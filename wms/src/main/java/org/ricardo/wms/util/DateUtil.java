package org.ricardo.wms.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 得到某一天的最后一秒钟
     */
    public static Date getEndDate(Date date){
        if(date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            date = calendar.getTime();
        }
        return date;
    }


    /**
     * 两个时间的间隔秒
     */
    public static int getBetweenTime(Date one, Date other) {
        return (int) (Math.abs(one.getTime() - other.getTime()) / 1000);
    }
}