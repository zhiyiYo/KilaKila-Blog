package com.zhiyiyo;

import com.zhiyiyo.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public class DateUtilTest {

    @Test
    public void testGetDateRange(){
        String yearMonth = "2021/1";
        try {
            Map<String, Date> dateRange = DateUtils.getDateRange(yearMonth);
            System.out.println(dateRange.get("start"));
            System.out.println(dateRange.get("end"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
