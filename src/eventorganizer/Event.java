package eventorganizer;

public class Event implements Comparable<Event>{
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    public Event (Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    @Override
    /**
     *
     * @return True if the events are the same, False otherwise.
     */
    public boolean equals(Event o){
        return (date.toString().equals(o.getDate().toString()) && startTime.toString().equals(o.getTimeSlot().toString()) && location.toString().equals(o.getLocation().toString()));
    }


    @Override
    public String toString(){
        EndTime endTime = new EndTime(startTime, duration);

        return "[Event Date: " + date.toString() + "] [Start Time: " + startTime.toString() + "] [End: " + endTime.toString() +"] @"
                + location.toString() + "(" + location.getBUILDING() + ", " + location.getCAMPUS() + ") ["
                + contact.getDepartment().getDEPARTMENTNAME() + ", " + contact.getEmail() + "]";
    }

    @Override
    public int compareTo(Event o){
        Date oDate = o.getDate();
        Timeslot oStartTime = o.getTimeSlot();

        int dateCompareResult = date.compareTo(oDate);
        int timeCompareResult = -2; //  a return of -2 means the date is not the same

        if (dateCompareResult == 0){ // if the dates are the same, compare the time slots
            timeCompareResult = startTime.compareTo(oStartTime);
        }

        return timeCompareResult;
    }

    public Date getDate() {
        return this.date;
    }

    public Timeslot getTimeSlot() {
        return this.startTime;
    }

    public Location getLocation() {
        return this.location;
    }

    public Contact getContact() {
        return this.contact;
    }

    public static void main(String [] args){
        test_InvalidDate();
        test_InvalidTime();
        test_InvalidLoc();
        test_Contact();
        test_Duration();
        test_DateTimeLocSame();

    }

    public static void test_InvalidDate(){
        Event testEvent1 = new Event(new Date(2023, 8, 15), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent1 = new Event(new Date(2023, 9, 29), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent1.equals(compareEvent1);

        System.out.println("**Test Case #1. Two different dates should not be equal.");
        testResult(testEvent1, expectedOut, actualOut);
    }

    public static void test_InvalidTime(){
        Event testEvent2 = new Event(new Date(2022, 6, 2), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent2 = new Event(new Date(2022, 6, 2), Timeslot.AFTERNOON, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent2.equals(compareEvent2);

        System.out.println("**Test Case #2. Two different dates should not be equal.");
        testResult(testEvent2, expectedOut, actualOut);
    }

    public static void test_InvalidLoc(){
        Event testEvent3 = new Event(new Date(2025, 3, 9), Timeslot.MORNING, Location.AB2225,
                new Contact("CS", "cs@rutgers.edu"), 30);
        Event compareEvent3 = new Event(new Date(2025, 3, 9), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = false;
        boolean actualOut = testEvent3.equals(compareEvent3);

        System.out.println("**Test Case #3. Two different locations should not be equal.");
        testResult(testEvent3, expectedOut, actualOut);
    }

    public static void test_Contact(){
        Event testEvent4 = new Event(new Date(2015, 11, 5), Timeslot.MORNING, Location.HLL114,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent4 = new Event(new Date(2015, 11, 5), Timeslot.MORNING, Location.HLL114,
                new Contact("CS", "cs@rutgers.edu"), 30);

        boolean expectedOut = true;
        boolean actualOut = testEvent4.equals(compareEvent4);

        System.out.println("**Test Case #4. Two different contacts should not impact equals().");
        testResult(testEvent4, expectedOut, actualOut);
    }

    public static void test_Duration(){
        Event testEvent5 = new Event(new Date(2020, 11, 26), Timeslot.AFTERNOON, Location.ARC103,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent5 = new Event(new Date(2020, 11, 26), Timeslot.AFTERNOON, Location.ARC103,
                new Contact("EE", "ee@rutgers.edu"), 45);

        boolean expectedOut = true;
        boolean actualOut = testEvent5.equals(compareEvent5);

        System.out.println("**Test Case #5. Two different durations should not impact equals().");
        testResult(testEvent5, expectedOut, actualOut);
    }

    public static void test_DateTimeLocSame(){
        Event testEvent6 = new Event(new Date(2021, 9, 8), Timeslot.EVENING, Location.MU302,
                new Contact("EE", "ee@rutgers.edu"), 30);
        Event compareEvent6 = new Event(new Date(2021, 9, 8), Timeslot.EVENING, Location.MU302,
                new Contact("EE", "ee@rutgers.edu"), 30);

        boolean expectedOut = true;
        boolean actualOut = testEvent6.equals(compareEvent6);

        System.out.println("**Test Case #6. Two events with the same date, time slot, and location should be equal.");
        testResult(testEvent6, expectedOut, actualOut);
    }

    private static boolean testResult(Event event, boolean expectedOutput, boolean actualOutput){
        if (expectedOutput == actualOutput){
            System.out.println("Passed!");
            return true;
        }
        System.out.println("Failed.");
        return false;
    }




}
