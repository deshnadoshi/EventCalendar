package eventorganizer;

/**
 Compares two events to determine their similarities.
 @author Deshna Doshi, Haejin Song
 */
public class Event implements Comparable<Event> {
    private Date date;
    private Timeslot startTime;
    private Location location;
    private Contact contact;
    private int duration;
    static final int DATE_NOT_EQUAL = -2;
    static final int EQUAL = 0;

    /**
     Constructor to initialize values of instance variables.
     @param date the date of the event.
     @param startTime the Timeslot of the event.
     @param location the Location of the event.
     @param contact the Contact of the event.
     @param duration the duration in minutes of the event.
     */
    public Event (Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Compares the date, startTime, and location of an event to determine if two events are equal.
     * @param checkEvent the Event that is compared to the object calling the class.
     * @return true if the date, startTime, and location of the event are equivalent, otherwise false.
     */
    @Override
    public boolean equals(Object checkEvent) {
        Event check_Event = (Event)checkEvent;
        return (date.toString().equals(check_Event.getDate().toString()) && startTime.toString().equals(check_Event.getTimeSlot().toString())
                && location.toString().equals(check_Event.getLocation().toString()));
    }
    /**
     * Converts the Event information into a String for display.
     * @return formatted String for an Event.
     */
    @Override
    public String toString() {
        EndTime endTime = new EndTime(startTime, duration);

        return "[Event Date: " + date.toString() + "] [Start Time: " + startTime.toString() + "] [End: " + endTime.toString() +"] @"
                + location.toString() + "(" + location.getBUILDING() + ", " + location.getCAMPUS() + ") ["
                + contact.getDepartment().getDEPARTMENTNAME() + ", " + contact.getEmail() + "]";
    }

    /**
     * First, determines if the dates are equal. If so, determines if the start times are equal.
     * @param checkEvent the Event that is compared to the object calling the class.
     * @return -1 if checkEvent is larger, 0 if the Events are equal, 1 if checkEvent is smaller
     */
    @Override
    public int compareTo(Event checkEvent) {
        Date oDate = checkEvent.getDate();
        Timeslot oStartTime = checkEvent.getTimeSlot();
        int dateCompareResult = date.compareTo(oDate);
        int timeCompareResult = DATE_NOT_EQUAL; //  a return of -2 means the date is not the same
        if (dateCompareResult == EQUAL) { // if the dates are the same, compare the time slots
            timeCompareResult = startTime.compareTo(oStartTime);
        }
        return timeCompareResult;
    }

    /**
     Getter for the date instance variable.
     @return the Date object.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     Getter for the startTime instance variable.
     @return the Timeslot object.
     */
    public Timeslot getTimeSlot() {
        return this.startTime;
    }

    /**
     Getter for the location instance variable.
     @return the Location object.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     Getter for the contact instance variable.
     @return the Contact object.
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     Getter for duration instance variable.
     @return the Duration in int.
     */
    public int getDuration() { return this.duration; }

    /**
     Testbed main() for the equals() method
     */
    public static void main(String [] args){
        test_InvalidDate();
        test_InvalidTime();
        test_InvalidLoc();
        test_Contact();
        test_Duration();
        test_DateTimeLocSame();

    }

    /**
     * Confirms that two Events with different dates are not equal.
     */
    public static void test_InvalidDate() {
        Event testEvent1 = new Event(new Date(2023, 8, 15), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent1 = new Event(new Date(2023, 9, 29), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent1.equals(compareEvent1);

        System.out.println("**Test Case #1. Two different dates should not be equal.");
        testResult(testEvent1, expectedOut, actualOut);
    }

    /**
     * Confirms that two Events with different start times are not equal.
     */
    public static void test_InvalidTime() {
        Event testEvent2 = new Event(new Date(2022, 6, 2), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent2 = new Event(new Date(2022, 6, 2), Timeslot.AFTERNOON, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent2.equals(compareEvent2);

        System.out.println("**Test Case #2. Two different dates should not be equal.");
        testResult(testEvent2, expectedOut, actualOut);
    }

    /**
     * Confirms that two Events with locations dates are not equal.
     */
    public static void test_InvalidLoc() {
        Event testEvent3 = new Event(new Date(2025, 3, 9), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent3 = new Event(new Date(2025, 3, 9), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent3.equals(compareEvent3);

        System.out.println("**Test Case #3. Two different locations should not be equal.");
        testResult(testEvent3, expectedOut, actualOut);
    }

    /**
     * Confirms that two Events with different contacts are not impacted by equals(), and are considered equal.
     */
    public static void test_Contact() {
        Event testEvent4 = new Event(new Date(2015, 11, 5), Timeslot.MORNING, Location.HLL114,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent4 = new Event(new Date(2015, 11, 5), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = true;
        boolean actualOut = testEvent4.equals(compareEvent4);

        System.out.println("**Test Case #4. Two different contacts should not impact equals().");
        testResult(testEvent4, expectedOut, actualOut);
    }

    /**
     * Confirms that two Events with different durations are not impacted by equals(), and are considered equal.
     */
    public static void test_Duration() {
        Event testEvent5 = new Event(new Date(2020, 11, 26), Timeslot.AFTERNOON, Location.ARC103,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent5 = new Event(new Date(2020, 11, 26), Timeslot.AFTERNOON, Location.ARC103,
                new Contact("EE", "ee@rutgers.edu"), 45);

        boolean expectedOut = true;
        boolean actualOut = testEvent5.equals(compareEvent5);

        System.out.println("**Test Case #5. Two different durations should not impact equals().");
        testResult(testEvent5, expectedOut, actualOut);
    }

    /**
     * Confirms that two Events with the same dates, start times, and locations are considered equal.
     */
    public static void test_DateTimeLocSame() {
        Event testEvent6 = new Event(new Date(2021, 9, 8), Timeslot.EVENING, Location.MU302,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent6 = new Event(new Date(2021, 9, 8), Timeslot.EVENING, Location.MU302,
                new Contact("EE", "ee@rutgers.edu"), 30);

        boolean expectedOut = true;
        boolean actualOut = testEvent6.equals(compareEvent6);

        System.out.println("**Test Case #6. Two events with the same date, time slot, and location should be equal.");
        testResult(testEvent6, expectedOut, actualOut);
    }

    /**
     * Determines if the test case was passed or failed.
     @param event the Event that is being checked for validity
     @param expectedOutput the predicted result of the equals() method
     @param actualOutput the actual result of the equals() method
     @return true if the predicted and actual result match, false otherwise.
     */
    private static boolean testResult(Event event, boolean expectedOutput, boolean actualOutput) {
        if (expectedOutput == actualOutput) {
            System.out.println("Passed!");
            return true;
        }
        System.out.println("Failed.");
        return false;
    }




}
