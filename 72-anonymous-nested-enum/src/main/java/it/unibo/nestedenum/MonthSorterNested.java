package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final int FIRST_POS = 0;
    private static final Comparator<String> SORT_MORDER = new SortByMonthOrder();
    private static final Comparator<String> SORT_DORDER = new SortByDate();


    private enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private int days;

        private Month(final int days){
            this.days = days;
        }

        public int getDays(){
            return this.days;
        }

        public static Month fromString(String month){
            List<Month> monthFound= new ArrayList<>();
            for(Month m : values()){
                if(m.name().compareToIgnoreCase(month) == 0){
                    monthFound.add(m);
                } else if (m.name().startsWith(month.toUpperCase())) {
                    monthFound.add(m);
                }
            }

            if(monthFound.isEmpty()){
                throw new IllegalArgumentException("No month with such name.");
            } else if (monthFound.size() > 1) {
                throw new IllegalArgumentException("Ambiguous month name.");
            }

            return monthFound.get(FIRST_POS);
        }
        
    }

    @Override
    public Comparator<String> sortByDays() {
        return SORT_DORDER;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return SORT_MORDER;
    }

    private static final class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }
        
    }

    private static final class SortByDate implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            int days1;
            int days2;
            days1 = Month.fromString(o1).days;
            days2 = Month.fromString(o2).days;
            return Integer.compare(days1, days2);
        }
        
    }

}
