package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringUtil {
  
  public static String NullToEmpty (String string) {
    if(string == null) {
      return "";
    }
    return string;
  }
  
  
  public static int StringToInt (String string) { // 스트링을 인트로 파싱해서 널일 경우 0으로 파싱해준다.
    int result;
    if(string == null || string.equals("")) {
      result = 0;
      return result;
    }
    result = Integer.parseInt(string);
    return result;
  }
  
  public static Date StringToDate (String string, String pattern) { 
    
    // pattern ex) "yyyy-MM-dd" 
    // string ex) "2018-07-19" 
    // => java.sql.date

    SimpleDateFormat format = new SimpleDateFormat(pattern);
    Date date = null;
    try {
      date = new java.sql.Date(format.parse(string).getTime());
    } catch (ParseException e) {
      System.out.println("StringToDate ERROR! PARSE EXCEPTION!");
      System.out.println("String =>" + string);
      e.printStackTrace();
    }
    return date;
  }
}
