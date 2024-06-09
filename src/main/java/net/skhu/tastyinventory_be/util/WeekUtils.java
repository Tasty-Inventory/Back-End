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



/* public class WeekOfMonthCalculator {
    public static String getCurrentWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.setTime(date);

        // 한 주의 시작은 월요일
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 첫 주에 최소 4일이 포함되어야 첫 주로 간주
        calendar.setMinimalDaysInFirstWeek(4);

        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작

        // 첫 주에 해당하지 않는 경우 전 달 마지막 주차로 계산
        if (weekOfMonth == 0) {
            calendar.add(Calendar.DATE, -calendar.get(Calendar.DATE)); // 전 달의 마지막 날 기준
            return getCurrentWeekOfMonth(calendar.getTime());
        }

        // 마지막 주차의 경우
        if (weekOfMonth == calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)) {
            calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); // 이번 달의 마지막 날
            int lastDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 이번 달 마지막 날의 요일

            // 마지막 날이 월~수 사이이면 다음달 1주차로 계산
            if (lastDayOfWeek >= Calendar.MONDAY && lastDayOfWeek <= Calendar.WEDNESDAY) {
                calendar.add(Calendar.DATE, 1); // 마지막 날 + 1일 => 다음달 1일
                return getCurrentWeekOfMonth(calendar.getTime());
            }
        }

        return month + "월 " + weekOfMonth + "주차";
    }
}*/
