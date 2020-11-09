package pro.paullezin.graduate.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    private DateTimeUtil() {
    }

    public static boolean checkTimeIfUserCanVoteNow() {
        return LocalDateTime.now().isBefore(LocalDate.now().atStartOfDay().plus(11, ChronoUnit.HOURS));
    }

}