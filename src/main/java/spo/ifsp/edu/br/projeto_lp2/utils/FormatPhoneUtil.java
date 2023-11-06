package spo.ifsp.edu.br.projeto_lp2.utils;

public class FormatPhoneUtil {
    public static String format(String phoneNumber) {
        String formattedPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        formattedPhoneNumber = "+55" + formattedPhoneNumber;
        return formattedPhoneNumber;
    }
}
