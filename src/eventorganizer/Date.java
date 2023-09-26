package eventorganizer;
import java.util.Calendar;

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public Date (int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isValid(){
        return (validCalendarDate() && withinSixMonths() && isFutureDate());
    }

    public boolean validCalendarDate(){
        if (validMonth() && validYear() && validDay()){
            return true;
        }

        return false;
    }

    public boolean validMonth(){
        final int MONTHS_PER_YEAR = 12;
        final int MIN_MONTH_VAL = 1;

        if(month <= MONTHS_PER_YEAR && month >= MIN_MONTH_VAL){
            return true;
        }

        return false;
    }

    // need to fix this so only one like of code per month -- can use calendar months and just add 1 to it
    public boolean validDay(){
        boolean isLeapYear = isLeapYear();
        final int JAN = 1; final int FEB = 2; final int MAR = 3; final int APR = 4; final int MAY = 5; final int JUN = 6;
        final int JUL = 7; final int AUG = 8; final int SEP = 9; final int OCT = 10; final int NOV = 11; final int DEC = 12;

        final int DAY_31_MONTHS = 31; final int DAY_30_MONTHS = 30; final int DAY_29_MONTHS = 29; final int DAY_28_MONTHS = 28;
        final int FIRST_DAY_OF_MONTH = 1;

        if (month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || month == OCT || month == DEC){
            if (day <= DAY_31_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (month == APR || month == JUN || month == SEP || month == NOV){
            if (day <= DAY_30_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (month == FEB){
            if (isLeapYear){
                if (day <= DAY_29_MONTHS && day >= FIRST_DAY_OF_MONTH){
                    return true;
                }
            } else {
                if (day <= DAY_28_MONTHS && day >= FIRST_DAY_OF_MONTH){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validYear(){
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);

        // since dates can only be for future, must be within 2023 and 2024 at the bare minimum
        final int MIN_YR = currentYear; final int MAX_YR = currentYear + 1;

        if (year == MIN_YR || year == MAX_YR){
            return true;
        }
        return false;
    }

    // need to make this method shorter !! can't be more than 40 lines
    public boolean withinSixMonths(){
        Calendar today = Calendar.getInstance();

        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH); // goes from 0 to 11
        int currentYear = today.get(Calendar.YEAR);

        final int REALIGN_CURR_MONTH = 1;
        currentMonth += REALIGN_CURR_MONTH; // Need to add 1 so that the current month uses 1 - Jan, 2 - Feb ... instead of 0 - Jan, 1 - Feb

        return (withinSixMonths_CompleteCheck(currentDay, currentMonth, currentYear));

    }
    public boolean withinSixMonths_MonthYearCheck(int currDay, int currMonth, int currYear){
        final int MAX_BOOKING_TIMEFRAME = 6;
        final int REALIGN = 1;
        final int MONTH_PER_YEAR = 12; // max 6 months ahead of current date you can book

        int sixMonthLimit = currMonth;
        int correspondMonthToYear = currYear;

        int [] possibleMonths = new int[MAX_BOOKING_TIMEFRAME + REALIGN]; // this array will contain all months that are within 6 months from the time frame
        int [] possibleYear = new int [MAX_BOOKING_TIMEFRAME + REALIGN];
        int arrayIndexer = possibleMonths.length - REALIGN;

        while (arrayIndexer >= 0){
            possibleMonths[arrayIndexer] = sixMonthLimit;
            possibleYear[arrayIndexer] = correspondMonthToYear; // populating list of all possible months and their corresponding years into an array to determine post-6 mos date
            sixMonthLimit++;

            if (sixMonthLimit > MONTH_PER_YEAR) {
                sixMonthLimit = REALIGN;
                correspondMonthToYear++;
            }
            arrayIndexer--;
        }

        currMonth += MAX_BOOKING_TIMEFRAME;
        if (currMonth >= MONTH_PER_YEAR){
            currMonth -= MONTH_PER_YEAR; // month number in the next year: month 7/2023 + 6 = 13 - 12 = 1 = Jan of 2024
            currYear++; // year also goes up by 1
        }
        if (year <= currYear){
            for (int i = 0; i < possibleMonths.length; i++){
                if (possibleMonths[i] == month && possibleYear[i] == year){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean withinSixMonths_CompleteCheck(int currDay, int currMonth, int currYear){
        final int MAX_BOOKING_TIMEFRAME = 6;
        final int MONTH_PER_YEAR = 12;
        final int REALIGN = 1;

        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH) + REALIGN;

        if (withinSixMonths_MonthYearCheck(currDay, currMonth, currYear)){
            if (month == todayMonth) {
                if (day >= currDay) {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }
    public boolean isFutureDate(){
        Calendar today = Calendar.getInstance();
        final int REALIGN_MONTHS = 1;
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH) + REALIGN_MONTHS; // goes from 0 to 11
        int currentYear = today.get(Calendar.YEAR);

        if(year > currentYear){
            return true;
        } else if (year == currentYear){
            if (month > currentMonth){
                return true;
            } else if (month == currentMonth){
                if (day > currentDay){
                    return true;
                }
            }
        }


        return false;
    }
    public boolean isLeapYear(){
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUATERCENTENNIAL = 400;

        if (year % QUADRENNIAL == 0){
            if (year % CENTENNIAL == 0){
                if (year % QUATERCENTENNIAL == 0){
                    return true;
                }
            }
        }

        return false;
    }
    public String toString(){
        return "";
    }
    @Override
    public int compareTo(Date o) {
        return 0;
    }

    public static void main(String [] args){


        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);
        int currentDay = today.get(Calendar.DAY_OF_MONTH);

        // System.out.println(currentDay);

        Date testDate1 = new Date(2023, 11, 8);
        System.out.println(testDate1.isValid());
        // saying that sept 26 is not within 6 months

    }
}
