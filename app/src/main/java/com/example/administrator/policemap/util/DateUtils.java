/**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.policemap.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author jingle1267@163.com
 */
public final class DateUtils {

    /**
     * 日期类型 *
     */
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm= "yyyy-MM-dd HH:mm";
    public static final String HHmm= "HH:mm";
    public static final String HHmmss = "HH:mm:ss";
    public static final String hhmmss = "HH:mm:ss";
    public static final String LOCALE_DATE_FORMAT = "yyyy年M月d日 HH:mm:ss";
    public static final String DB_DATA_FORMAT = "yyyy-MM-DD HH:mm:ss";
    public static final String NEWS_ITEM_DATE_FORMAT = "hh:mm M月d日 yyyy";


    public static String dateToString(Date date, String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String dateStr, String pattern) {
        if (dateStr.contains("T"))dateStr=getStringTime(dateStr);
        try {
            return new SimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 得到年
     *
     * @param date Date对象
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到月
     *
     * @param date Date对象
     * @return 月
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    /**
     * 得到日
     *
     * @param date Date对象
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换日期 将日期转为今天, 昨天, 前天, XXXX-XX-XX, ...
     *
     * @param time 时间
     * @return 当前日期转换为更容易理解的方式
     */

    public static String translateDate(Long time) {
        long oneDay = 24 * 60 * 60 * 1000;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long todayStartTime = today.getTimeInMillis();

        if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
            return "今天";
        } else if (time >= todayStartTime - oneDay && time < todayStartTime) { // yesterday
            return "昨天";
        } else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) { // the day before yesterday
            return "前天";
        } else if (time > todayStartTime + oneDay) { // future
            return "将来某一天";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            return dateFormat.format(date);
        }
    }

    /**
     * 转换日期 转换为更为人性化的时间
     *
     * @param time 时间
     * @return
     */
    public static String translateDate(long time, long curTime) {
        long oneDay = 24 * 60 * 60;
        Calendar today = Calendar.getInstance();    //今天
        today.setTimeInMillis(curTime * 1000);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        long todayStartTime = today.getTimeInMillis() / 1000;
        if (time >= todayStartTime) {
            long d = curTime - time;
            if (d <= 60) {
                return "1分钟前";
            } else if (d <= 60 * 60) {
                long m = d / 60;
                if (m <= 0) {
                    m = 1;
                }
                return m + "分钟前";
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("今天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        } else {
            if (time < todayStartTime && time > todayStartTime - oneDay) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("昨天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {

                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else  if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay){
                SimpleDateFormat dateFormat = new SimpleDateFormat("前天 HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(time * 1000);
                String dateStr = dateFormat.format(date);
                if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
                    dateStr = dateStr.replace(" 0", " ");
                }
                return dateStr;
            }
        }
    }

    public static boolean isValidDate(String inDate) {

        if (inDate == null)
            return false;

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (inDate.trim().length() != dateFormat.toPattern().length())
            return false;

        dateFormat.setLenient(false);

        try {
            //parse the inDate parameter
            dateFormat.parse(inDate.trim());
        }
        catch (ParseException pe) {
            return false;
        }
        return true;
    }
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|17[0678]\\d{8}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public static long getYesterday(long today){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(today));
        calendar.set(Calendar.DAY_OF_YEAR,-1);
        return calendar.getTime().getTime();
    }
    public static Long getStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    public static Long getEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime()+1000;
    }

    public static String getStringTime(String ss){
        String s=ss.replace("T"," ");
        int loc=s.indexOf(".");
        return  loc==-1?s:s.substring(0,loc);
    }

    public static String getStringTimeT(String ss){
        String s=ss.replace(" ","T");
        int loc=s.indexOf(".");
        return  loc==-1?s:s.substring(0,loc);
    }

    public static boolean isInTime(String ss,int range){
        Date date=stringToDate(getStringTime(ss),yyyyMMddHHmmss);
        long start=date.getTime()-range*60*1000;
        long end=date.getTime()+range*60*1000;
        long now=System.currentTimeMillis();

        return (now>=start&&now<=end);
    }


    public static boolean isAfterEndTime(String ss,int range){
        Date date=stringToDate(getStringTime(ss),yyyyMMddHHmmss);
        long end=date.getTime()+range*60*1000;
        long now=System.currentTimeMillis();

        return now>end;
    }

    public static boolean isFrontEndTime(String ss,int range){
        Date date = stringToDate(getStringTime(ss), yyyyMMddHHmmss);
        long start=date.getTime()-range*60*1000;
        long now=System.currentTimeMillis();

        return now<start;
    }

    public static String stringDateToGood(String ss){
        Date date= DateUtils.stringToDate(DateUtils.getStringTime(ss), DateUtils.yyyyMMddHHmmss);
        return DateUtils.translateDate(date.getTime()/1000,System.currentTimeMillis());
    }


    public static long getBackDay(long time){
        return time-86400000L;
    }

    public static String getGoodTimeFromString(String s){
        return DateUtils.translateDate(DateUtils.stringToDate(DateUtils.getStringTime(s), DateUtils.yyyyMMddHHmmss).getTime()/1000,System.currentTimeMillis()/1000);
    }

    public static String getGoodTimeFromLong(long l){
        return DateUtils.translateDate(l/1000,System.currentTimeMillis()/1000);
    }


    public static int getBetweenTimes(String start,String end){
        Date e=stringToDate(end,yyyyMMddHHmmss);
        Date s=stringToDate(start,yyyyMMddHHmmss);

        return (int)((e.getTime()-s.getTime())/1000/60);
    }

    public static int getNowWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isSameDay(int month,int day,String time){
        time=getStringTime(time);
        Date date=stringToDate(time,yyyyMMddHHmmss);

        if (getMonth(date)==month&&getDay(date)==day)return true;
        return false;
    }

    public static String tTimeGetHHmm(String t){
        String time;
        time=dateToString(stringToDate(getStringTime(t),yyyyMMddHHmmss),HHmm);
        return time;
    }

    public static String longToString(long t,String type){
        String time;
        time=dateToString(new Date(t),type);
        return time;
    }

    public static String getNowString(String type){
        String time;
        return dateToString(new Date(),type);
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF))|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

}
