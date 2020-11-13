package pro.paullezin.graduate.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public final class DateTimeUtil {
    private static final int HOURS_EXPIRED_TO_VOTE = 11;

    private DateTimeUtil() {
    }

    public static boolean checkTimeIfUserCanVoteNow() {
        return LocalDateTime.now().isBefore(LocalDate.now().atStartOfDay().plus(HOURS_EXPIRED_TO_VOTE, ChronoUnit.HOURS));
    }

}