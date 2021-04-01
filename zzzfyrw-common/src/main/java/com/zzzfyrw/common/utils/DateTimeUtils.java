package com.zzzfyrw.common.utils;


import com.zzzfyrw.common.exception.DateUnSupportException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * auth dpz
 * created 2020-01-19
 */
public class DateTimeUtils {

    public static final int DAY_OF_WEEK = 7;


    public static List<LocalDate> getAllDayByStartToEnd(LocalDate start,LocalDate end) {
        if(start.isAfter(end)){
            throw new DateUnSupportException("Start Is Not Allow After For End");
        }
        List<LocalDate> localDateList = new ArrayList<>();
        long day = end.toEpochDay()-start.toEpochDay();
        localDateList.add(start);
        for (long i = 1; i <= day; i++) {
            LocalDate nextDate = start.plus(i, ChronoUnit.DAYS);
            localDateList.add(nextDate);
        }
        return localDateList;
    }

    public static List<LocalDate> getAllDayOfWeekByDate(LocalDate date){
        if(date == null){
            throw new DateUnSupportException("Date Is Not Allow Null");
        }
        List<LocalDate> localDateList = new ArrayList<>();
        LocalDate firstDayOfWeek = date.with(WeekFields.of(Locale.FRANCE).getFirstDayOfWeek());
        localDateList.add(firstDayOfWeek);
        for (int i = 1; i < DAY_OF_WEEK; i++) {
            LocalDate nextDate = firstDayOfWeek.plus(i, ChronoUnit.DAYS);
            localDateList.add(nextDate);
        }
        return localDateList;
    }

    public static LocalDate getFirstDayOfWeekByDate(LocalDate date){
        if(date == null){
            throw new DateUnSupportException("Date Is Not Allow Null");
        }
        return date.with(WeekFields.of(Locale.FRANCE).getFirstDayOfWeek());
    }

    public static LocalDate getLastDayOfWeekByDate(LocalDate date){
        if(date == null){
            throw new DateUnSupportException("Date Is Not Allow Null");
        }
        LocalDate firstDayOfWeek = date.with(WeekFields.of(Locale.FRANCE).getFirstDayOfWeek());
        return firstDayOfWeek.plus(DAY_OF_WEEK - 1,ChronoUnit.DAYS);
    }

    public static LocalDate formmatStringToDate(String dateStr){
        String[] split = dateStr.split("-");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            if(Integer.parseInt(s) < 10){
                sb.append("0").append(s).append("-");
            }else {
                sb.append(s).append("-");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(sb.deleteCharAt(sb.length() - 1).toString(),formatter);
    }

    public static List<LocalDate> getAllDayOfWeekByNow(){
        return getAllDayOfWeekByDate(LocalDate.now());
    }

    public static LocalDate getFirstDayOfWeekByNow(){
        return getFirstDayOfWeekByDate(LocalDate.now());
    }

    public static LocalDate getLastDayOfWeekByNow(){
        return getLastDayOfWeekByDate(LocalDate.now());
    }

    /**
     * 获取年龄
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getAge(int year, int month, int day) {

        // 生日
        LocalDate birthday = LocalDate.of(year, month, day);

        // 当前日期
        LocalDate today = LocalDate.now();

        long duration = ChronoUnit.YEARS.between(birthday, today);

        return (int) duration;
    }

    public static int getAge(LocalDate birthday) {

        // 当前日期
        LocalDate today = LocalDate.now();

        long duration = ChronoUnit.YEARS.between(birthday, today);

        return (int) duration;
    }


    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2020,3,4);
        LocalDate end = LocalDate.of(2020,1,20);
//        List<LocalDate> listByStartToEnd = getAllDayByStartToEnd(start, end);
//        System.out.println(listByStartToEnd.toString());
//        System.out.println(getAllDayOfWeekByNow());
//        System.out.println(getAllDayOfWeekByDate(start));
//        System.out.println(getLastDayOfWeekByDate(start));
        LocalDate plus = start.plus(DateTimeUtils.DAY_OF_WEEK, ChronoUnit.DAYS);
        LocalDate minus = start.minus(DateTimeUtils.DAY_OF_WEEK, ChronoUnit.DAYS);
        System.out.println(plus);
        System.out.println(minus);
    }

}
