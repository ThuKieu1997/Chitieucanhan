package vn.edu.iuh.kieu.chitieucanhan.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public abstract class BaseController {

    private static final String dateRegEx="^[0-3]{1}[0-9]{1}/[0-1]{1}[0-2]{1}/[1-9]{1}[0-9]{3}$";

    public boolean isValidDate(String value) {
        return Pattern.matches(dateRegEx, value);
    }

    public boolean isValidNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String formatNumber (String pattern, double value) {
        NumberFormat formatter = new DecimalFormat(pattern);
        return formatter.format(value);
    }

    @Override
    public abstract String toString();
}
