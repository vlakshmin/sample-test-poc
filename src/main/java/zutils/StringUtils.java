package zutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
