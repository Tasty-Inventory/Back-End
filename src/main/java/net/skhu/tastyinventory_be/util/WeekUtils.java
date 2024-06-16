package net.skhu.tastyinventory_be.util;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekUtils {

    public static LocalDate getStartOfWeek(int year, int month, int week) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate firstWeekStart = firstDayOfMonth.with(weekFields.weekOfMonth(), 1);
        return firstWeekStart.plusWeeks(week - 1);
    }

    public static int getWeeksInMonth(int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int firstWeek = firstDayOfMonth.get(weekFields.weekOfMonth());
        int lastWeek = lastDayOfMonth.get(weekFields.weekOfMonth());
        return lastWeek - firstWeek + 1;
    }

    public static int changeWeek(int year, int month, int week, int direction) {
        int newWeek = week + direction;
        int newMonth = month;
        int newYear = year;

        if (newWeek > getWeeksInMonth(year, month)) {
            newWeek = 1;
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        } else if (newWeek < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newWeek = getWeeksInMonth(newYear, newMonth);
        }

        return newWeek;
    }
}