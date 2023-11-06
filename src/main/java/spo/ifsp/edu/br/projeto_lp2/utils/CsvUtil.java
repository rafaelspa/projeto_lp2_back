package spo.ifsp.edu.br.projeto_lp2.utils;

import spo.ifsp.edu.br.projeto_lp2.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CsvUtil {
    public static List<User> getUsersFromCsv(String csv) throws Exception {
        //csv: gender,name.title,name.first,name.last,location.street,location.city,location.state,location.postcode,location.coordinates.latitude,location.coordinates.longitude,location.timezone.offset,location.timezone.description,email,dob.date,dob.age,registered.date,registered.age,phone,cell,picture.large,picture.medium,picture.thumbnail

        List<String> lines = new ArrayList<>(Arrays.asList(csv.split("\r")));

        lines = getValidCsvLines(lines);

        List<User> users = new ArrayList<>();

        for (String line : lines) {

            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            for (int i = 0; i < fields.length - 1; i++)
                fields[i] = removeQuotesAndNewLineFromString(fields[i]);

            User user = new User();

            user.setGender(fields[0].charAt(0));
            user.setName(fields[1], fields[2], fields[3]);
            user.setLocation(fields[4], fields[5], fields[6], fields[7], Double.parseDouble(fields[8]), Double.parseDouble(fields[9]));
            user.setTimezone(fields[10], fields[11]);
            user.setEmail(fields[12]);
            user.setBirthday(DateUtil.getDateFromString(fields[13]));
            user.setRegistered(DateUtil.getDateFromString(fields[15]));
            user.setTelephoneNumbers(Arrays.asList(fields[17].split(";")));
            user.setMobilePhoneNumbers(Arrays.asList(fields[18].split(";")));
            user.setPicture(fields[19], fields[20], fields[21]);
            user.setNationality("BR");

            users.add(user);
        }

        return users;
    }

    private static List<String> getValidCsvLines(List<String> csvLines) {
        csvLines.remove(0);
        List<String> lines = getCsvLinesWithoutEmptyLines(csvLines);

        return lines;
    }

    private static List<String> getCsvLinesWithoutEmptyLines(List<String> csvLines) {
        Predicate<String> notAnEmptyLine = line -> line != null && !(line.trim().isEmpty() || line.trim().equals("\n"));

        return csvLines.stream().filter(notAnEmptyLine).collect(Collectors.toList());
    }

    private static String removeQuotesAndNewLineFromString(String field) {
        return field.replace("\"", "").replace("\n", "");
    }
}
