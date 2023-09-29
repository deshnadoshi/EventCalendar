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

    public boolean advancedIsValid(){
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

    public boolean validDay(){
        boolean isLeapYear = isLeapYear();
        Calendar monthInt = Calendar.getInstance();

        final int CALENDAR_CLASS_ALIGN = 1;
        month -= CALENDAR_CLASS_ALIGN;  // need to do this bc in calendar the months start from 0 index

        final int DAY_31_MONTHS = 31; final int DAY_30_MONTHS = 30; final int DAY_29_MONTHS = 29; final int DAY_28_MONTHS = 28;
        final int FIRST_DAY_OF_MONTH = 1;

        if (month == monthInt.JANUARY || month == monthInt.MARCH || month == monthInt.MAY || month == monthInt.JULY || month == monthInt.AUGUST || month == monthInt.OCTOBER || month == monthInt.DECEMBER){
            if (day <= DAY_31_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (month == monthInt.APRIL || month == monthInt.JUNE || month == monthInt.SEPTEMBER || month == monthInt.NOVEMBER){
            if (day <= DAY_30_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (month == monthInt.FEBRUARY){
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
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean isValid(){
        return validDay() && validMonth() && basicValidYear();
    }

    public boolean basicValidYear(){
        final int MIN_YEAR = 1900;
        final int MAX_YEAR = 2100;

        if(year >= MIN_YEAR && year <= MAX_YEAR){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return month + "/" + day +"/" + year;
    }

    @Override
    public int compareTo(Date o) {
        // A.compareTo(B) if -1: A < B; 1: A > B, 0: A = B
        int EQUAL = 1;
        if (this.year == o.year){
            if (this.month == o.month){
                if (this.day == o.day){
                    EQUAL = 0; // they are same 2 same
                } else if (this.day < o.day){
                    EQUAL = -1; // o > this
                }
            } else if (this.month < o.month){
                EQUAL = -1; // o date is after this
            }
        } else if (this.year < o.year){
            EQUAL = -1; // o date is after this
        }
        return EQUAL;
    }

    public static void main(String [] args){
        test_MinYear();
        test_MaxYear();
        test_NonLeapYearFeb();;
        test_MonthRange();
        test_31DayRange();
        test_30DayRange();
        test_MinMonthVal();
        test_NonNegDate();
        test_LeapYearFeb();
        test_RealDate();

    }

    private static void test_MinYear(){
        Date testCase1 = new Date(1832,9,26);
        boolean expectedOut = false;
        boolean actualOut = testCase1.isValid();
        System.out.println("**Test case #1. A year before 1900 should be invalid.");
        testResult(testCase1, expectedOut, actualOut);

    }

    private static void test_MaxYear() {
        Date testCase2 = new Date(2154, 6, 11);
        boolean expectedOut = false;
        boolean actualOut = testCase2.isValid();
        System.out.println("**Test case #2. A year after 2100 should be invalid.");
        testResult(testCase2, expectedOut, actualOut);
    }

    private static void test_NonLeapYearFeb() {
        Date testCase3 = new Date(2023, 2, 29);
        boolean expectedOut = false;
        boolean actualOut = testCase3.isValid();
        System.out.println("**Test case #3. Number of days in February for a non-leap year is 28.");
        testResult(testCase3, expectedOut, actualOut);
    }

    private static void test_MonthRange(){
        Date testCase4 = new Date(2012,13,2);
        boolean expectedOut = false;
        boolean actualOut = testCase4.isValid();
        System.out.println("**Test case #4. The month must be within the range of  1 to 12.");
        testResult(testCase4, expectedOut, actualOut);
    }

    private static void test_31DayRange(){
        Date testCase5 = new Date(2009,7,33);
        boolean expectedOut = false;
        boolean actualOut = testCase5.isValid();
        System.out.println("**Test case #5. The day should not be greater than 31 for the following months: Jan, " +
                "Mar, May, Jul, Aug, Oct, Dec.");
        testResult(testCase5, expectedOut, actualOut);
    }

    private static void test_30DayRange(){
        Date testCase6 = new Date(2025,6,31);
        boolean expectedOut = false;
        boolean actualOut = testCase6.isValid();
        System.out.println("**Test case #6. The day should not be greater than 30 for the following months: Apr, " +
                "Jun, Sep, Nov.");
        testResult(testCase6, expectedOut, actualOut);
    }

    private static void test_MinMonthVal(){
        Date testCase7 = new Date(2026,9,0);
        boolean expectedOut = false;
        boolean actualOut = testCase7.isValid();
        System.out.println("**Test case #7. The day should not be less than 1 for any of the months.");
        testResult(testCase7, expectedOut, actualOut);
    }

    private static void test_NonNegDate(){
        Date testCase8 = new Date(2002,10,-5);
        boolean expectedOut = false;
        boolean actualOut = testCase8.isValid();
        System.out.println("**Test case #8. A negative number of the month, day, or year is invalid.");
        testResult(testCase8, expectedOut, actualOut);
    }

    private static void test_LeapYearFeb(){
        Date testCase9 = new Date(2024,2,29);
        boolean expectedOut = true;
        boolean actualOut = testCase9.isValid();
        System.out.println("**Test case #9. For a leap year, February is permitted to have 29 days.");
        testResult(testCase9, expectedOut, actualOut);
    }

    private static void test_RealDate(){
        Date testCase10 = new Date(2023,9,26);
        boolean expectedOut = true;
        boolean actualOut = testCase10.isValid();
        System.out.println("**Test case #10. A legitimate calendar date must be recognized as valid.");
        testResult(testCase10, expectedOut, actualOut);
    }

    private static boolean testResult(Date date, boolean expectedOutput, boolean actualOutput){
        if (expectedOutput == actualOutput){
            System.out.println("Passed!");
            return true;
        }
        System.out.println("Failed.");
        return false;
    }
}
