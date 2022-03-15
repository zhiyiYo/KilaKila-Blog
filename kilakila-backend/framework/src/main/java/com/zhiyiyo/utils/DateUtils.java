package com.zhiyiyo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
    private DateUtils() {
    }

    /**
     * 根据月份获取日期区间
     *
     * @param yearMonth <code>年份/月份<code/> 格式的日期字符串
     * @return dataRange 由 <code>start</code> 和 <code>end</code> 组成的日期范围字典
     */
    public static Map<String, Date> getDateRange(String yearMonth) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
        Date date = dateFormat.parse(yearMonth);

        Map<String, Date> dateRange = new HashMap<>();
        dateRange.put("start", new Date(date.getYear(), date.getMonth(), 1));

        int year = date.getYear() + date.getMonth() / 11;
        int month = (date.getMonth() + 1) % 12;
        dateRange.put("end", new Date(year, month, 1));
        return dateRange;
    }
}
