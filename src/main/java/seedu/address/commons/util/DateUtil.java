package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private final String followUp;
    private final String date;

    /**
     * This is a date test.
     */
    public DateUtil(String followUp, String date) {
        this.followUp = followUp;
        this.date = date;
    }


    /**
     * return true if need to call today.
     */
    public boolean lastDay() {
        LocalDate today = LocalDate.now();
        String date2 = date.trim();
        int day = Integer.parseInt(followUp.trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate reportDate = LocalDate.parse(date2, formatter);
        LocalDate temp = reportDate;
        if (reportDate.isEqual(today)) {
            return true;
        } else {
            while (temp.isBefore(today) == true) {
                temp = temp.plusDays(day);
            }
            if (temp.isEqual(today)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
