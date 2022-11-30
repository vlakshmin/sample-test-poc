package zutils;

public final class StringUtils {

    public static String getFilterHeader(String column) {

        return String.format("Filter by %s",column);
    }
}
