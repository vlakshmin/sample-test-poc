package zutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.format;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public final class StringUtils {
    private static final SimpleDateFormat incomingSdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat outgoingSdf = new SimpleDateFormat("MMM d yyyy");

    public static String getFilterHeader(String column) {

        return String.format("Filter by %s", column);
    }

    public static String parsedDate(String date) {

        try {
            Date formattedDate = incomingSdf.parse(date);

            return outgoingSdf.format(formattedDate);
        } catch (ParseException e) {

            throw new RuntimeException(e);
        }
    }

    public static String getStringLastDayOfTheCurrentMonth(){
        ZonedDateTime currentDate = getUTCZonedCurrentDate("UTC");
        LocalDate initial = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth());

        return String.valueOf(initial.with(lastDayOfMonth()).format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    public static String getStringPreviousMonth(){
        ZonedDateTime currentDate = getUTCZonedCurrentDate("UTC");

        return format("%s %s",
                currentDate.minusMonths(1).getMonth().getDisplayName(TextStyle.FULL, Locale.US),
                currentDate.getMonth().getValue() == 1 ? currentDate.getYear() - 1 : currentDate.getYear());
    }

    public static String getStringCurrentDate(){
        ZonedDateTime currentDate = getUTCZonedCurrentDate("UTC");

        return format("%s %s",
                currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US), currentDate.getYear());

    }

    private static ZonedDateTime getUTCZonedCurrentDate(String timeZone){

        return  ZonedDateTime.now(ZoneId.of(timeZone));
    }
}
