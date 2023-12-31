package eventorganizer;
import java.util.Calendar;

/**
 Determines validity of a given date given the day, month, and year.
 @author Deshna Doshi, Haejin Song
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    private static final int MONTHS_PER_YEAR = 12;
    private static final int MAX_BOOKING_TIMEFRAME = 6;
    private static final int MIN_MONTH_VAL = 1;
    private static final int CALENDAR_CLASS_ALIGN = 1;
    private static final int DAY_31_MONTHS = 31;
    private static final int DAY_30_MONTHS = 30;
    private static final int DAY_29_MONTHS = 29;
    private static final int DAY_28_MONTHS = 28;
    private static final int FIRST_DAY_OF_MONTH = 1;
    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int MIN_YEAR = 1900;
    private static final int MAX_YEAR = 2100;

    /**
     Constructor to initialize values of instance variables.
     @param year the year of the date.
     @param month the month of the date.
     @param day the day of the date.
     */
    public Date (int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     Determines if the date is a valid calendar date, within six months, and in the future.
     @return true if the date is valid, within six months, and in the future, false otherwise.
     */
    public boolean advancedIsValid(){
        if(!validCalendarDate()){
            // System.out.println("Invalid calendar date.");
        } else if (!withinSixMonths()){
            // System.out.println("Not wihtin six months.");
        } else if (!isFutureDate()){
            // System.out.println("Not a future date.");
        }
        return (validCalendarDate() && withinSixMonths() && isFutureDate());
    }

    /**
     Determines if the date is a legitimate calendar date.
     @return true if the date is a real date, false otherwise.
     */
    public boolean validCalendarDate(){
        if (validMonth() && validYear() && validDay()){
            return true;
        }
        return false;
    }

    /**
     Determines if the month of the date is valid.
     @return true if the month is valid, false otherwise.
     */
    public boolean validMonth(){
        if(month <= MONTHS_PER_YEAR && month >= MIN_MONTH_VAL){
            return true;
        }
        return false;
    }

    /**
     Determines if the day of the date is valid.
     @return true if the day is valid, false otherwise.
     */
    public boolean validDay(){
        boolean isLeapYear = isLeapYear();
        Calendar monthInt = Calendar.getInstance();
        int alignMonth = month;
        alignMonth -= CALENDAR_CLASS_ALIGN;  // need to do this bc in calendar the months start from 0 index

        if (alignMonth == monthInt.JANUARY || alignMonth == monthInt.MARCH || alignMonth == monthInt.MAY || alignMonth == monthInt.JULY || alignMonth == monthInt.AUGUST || alignMonth == monthInt.OCTOBER || alignMonth == monthInt.DECEMBER){
            if (day <= DAY_31_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (alignMonth == monthInt.APRIL || alignMonth == monthInt.JUNE || alignMonth == monthInt.SEPTEMBER || alignMonth == monthInt.NOVEMBER){
            if (day <= DAY_30_MONTHS && day >= FIRST_DAY_OF_MONTH){
                return true;
            }
        } else if (alignMonth == monthInt.FEBRUARY){
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

    /**
     Determines if the year of the date is valid.
     @return true if the year is valid, false otherwise.
     */
    public boolean validYear(){
        Calendar today = Calendar.getInstance();
        int currentYear = today.get(Calendar.YEAR);

        // since dates can only be for future, must be within 2023 and 2024 at the bare minimum
        int MIN_YR = currentYear;
        int MAX_YR = currentYear + CALENDAR_CLASS_ALIGN;

        if (year == MIN_YR || year == MAX_YR){
            return true;
        }
        return false;
    }

    /**
     Determines if the date is within six months of today's date.
     @return true if the date is within six months, false otherwise.
     */
    public boolean withinSixMonths(){
        Calendar today = Calendar.getInstance();
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH) + CALENDAR_CLASS_ALIGN; // goes from 0 to 11
        // int currentYear = today.get(Calendar.YEAR);

        today.add(Calendar.MONTH, 6);
        // System.out.println(today.getTime() + " six months fro now");
        int sixMonthsDay = today.get(Calendar.DAY_OF_MONTH);
        int sixMonthsMonth = today.get(Calendar.MONTH) + CALENDAR_CLASS_ALIGN;
        int sixMonthsYear = today.get(Calendar.YEAR);

        // System.out.println(sixMonthsDay + " " + sixMonthsMonth + " "  + sixMonthsYear);

        if (year == sixMonthsYear){
            if (month < sixMonthsMonth){
                return true;
            } else if (month == sixMonthsMonth){
                if (day <= sixMonthsDay){
                    return true;
                }
            }
        } else if (year < sixMonthsYear){
            if(isFutureDate()){
                if(month > currentMonth){
                    return true;
                } else if (month == currentMonth){
                    if (day >= currentDay){
                        return true;
                    }
                }
            }
        }

        // currentMonth += CALENDAR_CLASS_ALIGN; // Need to add 1 so that the current month uses 1 - Jan, 2 - Feb ... instead of 0 - Jan, 1 - Feb

        return false;
    }

    /**
     Determines if the month and year of the date are within six months.
     @param currDay the day of today's date.
     @param currMonth the month of today's date.
     @param currYear the year of today's date.
     @return true if the month and year are within six months, false otherwise.
     */
    public boolean withinSixMonths_MonthYearCheck(int currDay, int currMonth, int currYear){
        int sixMonthLimit = currMonth;
        int correspondMonthToYear = currYear;

        int [] possibleMonths = new int[MAX_BOOKING_TIMEFRAME + CALENDAR_CLASS_ALIGN]; // this array will contain all months that are within 6 months from the time frame
        int [] possibleYear = new int [MAX_BOOKING_TIMEFRAME + CALENDAR_CLASS_ALIGN];
        int arrayIndexer = possibleMonths.length - CALENDAR_CLASS_ALIGN;

        while (arrayIndexer >= 0){
            possibleMonths[arrayIndexer] = sixMonthLimit;
            possibleYear[arrayIndexer] = correspondMonthToYear; // populating list of all possible months and their corresponding years into an array to determine post-6 mos date
            sixMonthLimit++;

            if (sixMonthLimit > MONTHS_PER_YEAR) {
                sixMonthLimit = CALENDAR_CLASS_ALIGN;
                correspondMonthToYear++;
            }
            arrayIndexer--;
        }

        currMonth += MAX_BOOKING_TIMEFRAME;
        if (currMonth >= MONTHS_PER_YEAR){
            currMonth -= MONTHS_PER_YEAR; // month number in the next year: month 7/2023 + 6 = 13 - 12 = 1 = Jan of 2024
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

    /**
     Determines if the day of the date (given the month and year are within six months) is within six months.

     @return true if the day (and month and year) are within six months, false otherwise.
     */
    public boolean withinSixMonths_CompleteCheck(/*int currDay, int currMonth, int currYear*/){

        Calendar today = Calendar.getInstance();
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH) + CALENDAR_CLASS_ALIGN;
        int todayYear = today.get(Calendar.YEAR);

        if (withinSixMonths_MonthYearCheck(todayDay, todayMonth, todayYear)){
            if (month == todayMonth) {
                if (day >= todayDay) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     Determines if a date is a future date.
     @return true if the date is in the future, false otherwise.
     */
    public boolean isFutureDate(){
        Calendar today = Calendar.getInstance();
        int currentDay = today.get(Calendar.DAY_OF_MONTH);
        int currentMonth = today.get(Calendar.MONTH) + CALENDAR_CLASS_ALIGN; // goes from 0 to 11
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

    /**
     Determines if the year is a leap year.
     @return true if year is a leap year, false otherwise.
     */
    public boolean isLeapYear(){

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

    /**
     Determines if the day, month, and year are legitimate calendar days, months, and years.
     @return true if the date is a legitimate date, false otherwise.
     */
    public boolean isValid(){
        return validDay() && validMonth() && basicValidYear();
    }

    /**
     Determines if the year is within 1900 and 2100.
     Arbitrarily set guidelines for determining a valid year.
     @return true if the year is within 1900 and 2100, false otherwise.
     */
    public boolean basicValidYear(){
        if(year >= MIN_YEAR && year <= MAX_YEAR){
            return true;
        }
        return false;
    }

    /**
     Converts the date into a "X/X/XXXX" formatted String.
     @return the date as a String.
     */
    @Override
    public String toString(){
        return month + "/" + day +"/" + year;
    }

    /**
     Determines if two dates are equivalent.
     @param checkDate the date that is being compared against the date calling the method.
     @return -1 if checkDate is larger, 0 if the dates are equal, 1 if checkDate is smaller.
     */
    @Override
    public int compareTo(Date checkDate) {
        // A.compareTo(B) if -1: A < B; 1: A > B, 0: A = B
        int EQUAL = 1;
        if (this.year == checkDate.year){
            if (this.month == checkDate.month){
                if (this.day == checkDate.day){
                    EQUAL = 0; // they are same 2 same
                } else if (this.day < checkDate.day){
                    EQUAL = -1; // o > this
                }
            } else if (this.month < checkDate.month){
                EQUAL = -1; // o date is after this
            }
        } else if (this.year < checkDate.year){
            EQUAL = -1; // o date is after this
        }
        return EQUAL;
    }

    /**
     Testbed main() to check functionality of the isValid() method.
     */
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

    /**
     Checks if a year before 1900 is invalid.
     */
    private static void test_MinYear(){
        Date testCase1 = new Date(1832,9,26);
        boolean expectedOut = false;
        boolean actualOut = testCase1.isValid();
        System.out.println("**Test case #1. A year before 1900 should be invalid.");
        testResult(testCase1, expectedOut, actualOut);

    }

    /**
     Checks if a year after 2100 is valid.
     */
    private static void test_MaxYear() {
        Date testCase2 = new Date(2154, 6, 11);
        boolean expectedOut = false;
        boolean actualOut = testCase2.isValid();
        System.out.println("**Test case #2. A year after 2100 should be invalid.");
        testResult(testCase2, expectedOut, actualOut);
    }

    /**
     Confirms that a non-leap year does not have more than 28 days.
     */
    private static void test_NonLeapYearFeb() {
        Date testCase3 = new Date(2023, 2, 29);
        boolean expectedOut = false;
        boolean actualOut = testCase3.isValid();
        System.out.println("**Test case #3. Number of days in February for a non-leap year is 28.");
        testResult(testCase3, expectedOut, actualOut);
    }

    /**
     Confirms that a month is within a range of 1-12.
     */
    private static void test_MonthRange(){
        Date testCase4 = new Date(2012,13,2);
        boolean expectedOut = false;
        boolean actualOut = testCase4.isValid();
        System.out.println("**Test case #4. The month must be within the range of  1 to 12.");
        testResult(testCase4, expectedOut, actualOut);
    }

    /**
     Confirms that Jan, Mar, May, Jul, Aug, Oct, Dec do not have more than 31 days.
     */
    private static void test_31DayRange(){
        Date testCase5 = new Date(2009,7,33);
        boolean expectedOut = false;
        boolean actualOut = testCase5.isValid();
        System.out.println("**Test case #5. The day should not be greater than 31 for the following months: Jan, " +
                "Mar, May, Jul, Aug, Oct, Dec.");
        testResult(testCase5, expectedOut, actualOut);
    }

    /**
     Confirms that Apr, Jun, Sep, Nov do not have more than 30 days.
     */
    private static void test_30DayRange(){
        Date testCase6 = new Date(2025,6,31);
        boolean expectedOut = false;
        boolean actualOut = testCase6.isValid();
        System.out.println("**Test case #6. The day should not be greater than 30 for the following months: Apr, " +
                "Jun, Sep, Nov.");
        testResult(testCase6, expectedOut, actualOut);
    }

    /**
     Confirms that the day is not less than 1 for any month.
     */
    private static void test_MinMonthVal(){
        Date testCase7 = new Date(2026,9,0);
        boolean expectedOut = false;
        boolean actualOut = testCase7.isValid();
        System.out.println("**Test case #7. The day should not be less than 1 for any of the months.");
        testResult(testCase7, expectedOut, actualOut);
    }

    /**
     Confirms that a day is not negative.
     */
    private static void test_NonNegDate(){
        Date testCase8 = new Date(2002,10,-5);
        boolean expectedOut = false;
        boolean actualOut = testCase8.isValid();
        System.out.println("**Test case #8. A negative number of the month, day, or year is invalid.");
        testResult(testCase8, expectedOut, actualOut);
    }


    /**
     Confirms that a leap-year can have 29 days.
     */
    private static void test_LeapYearFeb(){
        Date testCase9 = new Date(2024,2,29);
        boolean expectedOut = true;
        boolean actualOut = testCase9.isValid();
        System.out.println("**Test case #9. For a leap year, February is permitted to have 29 days.");
        testResult(testCase9, expectedOut, actualOut);
    }

    /**
     Confirms that any arbitrary and legitimate date is valid.
     */
    private static void test_RealDate(){
        Date testCase10 = new Date(2023,9,26);
        boolean expectedOut = true;
        boolean actualOut = testCase10.isValid();
        System.out.println("**Test case #10. A legitimate calendar date must be recognized as valid.");
        testResult(testCase10, expectedOut, actualOut);
    }

    /**
     * Determines if the test case was passed or failed.
     @param date the date that is being checked for validity
     @param expectedOutput the predicted result of the isValid() method
     @param actualOutput the actual result of the isValid() method
     @return true if the predicted and actual result match, false otherwise.
     */
    private static boolean testResult(Date date, boolean expectedOutput, boolean actualOutput){
        if (expectedOutput == actualOutput){
            System.out.println("Passed!");
            return true;
        }
        System.out.println("Failed.");
        return false;
    }
}
