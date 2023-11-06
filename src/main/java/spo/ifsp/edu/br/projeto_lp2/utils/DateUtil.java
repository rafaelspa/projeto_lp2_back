package spo.ifsp.edu.br.projeto_lp2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getDateFromString(String date) throws Exception {
        date = date.replaceAll("T", " ").replaceAll("Z", "");
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
    }
}
