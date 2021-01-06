package goodstrade.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    //	格式化日期格式
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //1.字符串转换为util.Date
    public static java.util.Date strToUtil(String str) {
        try {
            //  通过SimpleDateFormat对象的parse()方法将"字符串"转换为
            //	"java.util.Date"类型的日期格式
            java.util.Date date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //2.将util.Date转换为sql.Date
    public static java.sql.Date utilToSql(java.util.Date date) {
        return new java.sql.Date(date.getTime());

    }

    //3.将util.Date转换成字符串
    public static String utilToString(java.util.Date date) {
        return sdf.format(date);

    }

    //4.将字符串转换成格式化日期形式
    public static String formatStr(String str) {
        String str1 = str.substring(0, 4);
        String str2 = str.substring(4, 6);
        String str3 = str.substring(6);
        return str1 + "-" + str2 + "-" + str3;
    }
}
