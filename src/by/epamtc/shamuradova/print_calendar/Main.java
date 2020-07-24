package by.epamtc.shamuradova.print_calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        printCalendar(2020, "RU");
    }

    public static void printCalendar(int year, String local) {
        Locale localeRU = new Locale(local);
        Calendar gregor = new GregorianCalendar(year, 0, 0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");

        final int nextDay = 1;
        final int firstDayOfMonth = 1;
        final int secondDayOfMonthIsMon = -1;//поменяь название переменной

        for (int month = 0; month <= gregor.getMaximum(Calendar.MONTH); month++) {
            System.out.println();
            System.out.println(nameOfMonth(month, localeRU)); //выводим на русском название месяца. Нужно увеличить первую букву и заменить окончание
            gregor.add(Calendar.MONTH, 1);
            int daysInMonth = gregor.getActualMaximum(Calendar.DAY_OF_MONTH);
            System.out.println(monthAndDaysOfWeek(month)); //можно и без этого метода обойтись

            for (int day = 1; day <= daysInMonth; day++) {
                gregor.set(year, month, day);
                Date date = gregor.getTime();

                int dayOfWeek = gregor.get(Calendar.DAY_OF_WEEK) - nextDay;
                int nextDayOfWeek = dayOfWeek - nextDay;

                if (day == firstDayOfMonth && nextDayOfWeek != secondDayOfMonthIsMon) {
                    printFirstDayOfMonth(dayOfWeek, gregor, simpleDateFormat, date, false);

                } else if (day == firstDayOfMonth && dayOfWeek == 0) {
                    printFirstDayOfMonth(dayOfWeek, gregor,simpleDateFormat, date, true);

                } else if (dayOfWeek == 0) {
                    printSunday(simpleDateFormat, date, day);
//изменить: магические значения, разбить на методы
                } else if (dayOfWeek == 1) {
                    if (day < 10) {
                        System.out.print(" " + new StringBuilder(simpleDateFormat.format(date)));
                    } else {
                        System.out.print(new StringBuilder(simpleDateFormat.format(date)));
                    }
                } else {
                    if (day < 10) {
                        System.out.print("   " + new StringBuilder(simpleDateFormat.format(date)));
                    } else {
                        System.out.print("  " + new StringBuilder(simpleDateFormat.format(date)));
                    }
                }

            }
            System.out.println();
        }
    }

    private static void printSunday(SimpleDateFormat simpleDateFormat, Date date, int day) {
        StringBuilder formatDate = new StringBuilder(simpleDateFormat.format(date));
        formatDate.append("\n");

        if (day < 10) {
            System.out.print("   " + formatDate);
        } else {
            System.out.print("  " + formatDate);
        }
    }


    private static void printFirstDayOfMonth(int dayOfWeek, Calendar gregor, SimpleDateFormat simpleDateFormat, Date date, boolean isSunday) {
        StringBuilder delimiter = increaseDelimiter(dayOfWeek, gregor.get(Calendar.DAY_OF_MONTH));
        delimiter.append(simpleDateFormat.format(date));
        if (isSunday) {
            System.out.println(delimiter);
        } else {
            System.out.print(delimiter);
        }
    }


    public static String nameOfMonth(int month, Locale locale) throws IllegalArgumentException {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.MONTH, month);
        String monthName = c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);

        return monthName;
    }

    public static StringBuilder increaseDelimiter(int dayOfWeek, int dayOfMonth) {//проверить на валидность
        if (dayOfMonth < 10) {
            StringBuilder initialSpace = new StringBuilder(" ");
            StringBuilder addSpace = new StringBuilder("    ");//4

            if (dayOfWeek == 1) return initialSpace;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            for (int i = 1; i < dayOfWeek; i++) {
                if (i == 1) {
                    initialSpace.append("    ");//5 магическое зн
                }//4
                else {
                    initialSpace.append(addSpace);
                }
            }
            return initialSpace;

        } else {
            StringBuilder initialSpace = new StringBuilder("");
            StringBuilder addSpace = new StringBuilder("  ");//2

            if (dayOfWeek == 1) return initialSpace;

            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }

            for (int i = 1; i < dayOfWeek; i++) {
                if (i == 1) {
                    initialSpace.append("    ");//4 маг
                } else {
                    initialSpace.append(addSpace);
                }
            }
            return initialSpace;
        }

    }

    public static StringBuilder monthAndDaysOfWeek(int month) {
        StringBuilder monthRes;
        StringBuilder daysOfWeek = new StringBuilder("пн  вт  ср  чт  пт  сб  вс");
        switch (month) {
            case 0:
                monthRes = new StringBuilder("January");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 1:
                monthRes = new StringBuilder("February");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 2:
                monthRes = new StringBuilder("March");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 3:
                monthRes = new StringBuilder("April");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 4:
                monthRes = new StringBuilder("May");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 5:
                monthRes = new StringBuilder("June");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 6:
                monthRes = new StringBuilder("July");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 7:
                monthRes = new StringBuilder("August");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 8:
                monthRes = new StringBuilder("September");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 9:
                monthRes = new StringBuilder("October");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 10:
                monthRes = new StringBuilder("November");
                monthRes.append("\n").append(daysOfWeek);
                break;
            case 11:
                monthRes = new StringBuilder("December");
                monthRes.append("\n").append(daysOfWeek);
                break;
            default:
                monthRes = null;

        }
        return monthRes;
    }
}
