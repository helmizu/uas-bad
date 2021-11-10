package Num3;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Num3 {
    public static String formatter(Date date, String pattern, Locale locale, TimeZone tz) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
        sdf.setTimeZone(tz);  
        return sdf.format(date);
    }
    
    public static void main(String args[]) {
      Date date = new Date();
      String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
      Locale locale = new Locale("en", "SG");
      TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
      String dateFormatted = formatter(date, pattern, locale, tz);
      System.out.println(dateFormatted);
    }
}