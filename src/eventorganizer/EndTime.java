package eventorganizer;

/**
 Calculates the end time of an Event based on the start time and duration of the Event.
 @author Deshna Doshi, Haejin Song
 */
public class EndTime {
    // class to calculate the ending time for a given input timeslot and the duration timeslot
    private Timeslot startTime;
    private int duration;
    private int endHour;
    private int endMin;
    private boolean AM = true;
    private final int TIME_PARAMETERS = 2;
    private static final String AM_LABEL = "am";
    private static final String PM_LABEL = "pm";
    private static final int MIN_PER_HOUR = 60;
    private static final int AM_PM_SWITCH = 12;
    private static final int CHECK_TIME_SINGLE_DIGIT = 0;

    /**
     * Constructor that initializes the instance variables and determines the time group of the Event (AM vs. PM).
     * @param startTime the start time of the Event.
     * @param duration the duration of the Event in minutes.
     */
    public EndTime(Timeslot startTime, int duration){
        this.startTime = startTime;
        this.duration = duration;
        if (startTime.toString().equals("AFTERNOON") || startTime.toString().equals("EVENING") ){
            AM = false;
        }

    }

    /**
     * Calculate the end time of the Event.
     * @return integer array with the ending hour (index 0) and ending minute (index 1) of the Event.
     */
    public int [] calcEndTime(){
        int startHour = startTime.HOUR;
        int startMin = startTime.MIN;

        while (duration >= MIN_PER_HOUR){
            duration -= MIN_PER_HOUR;
            startHour++;
        }

        startMin += duration;

        if (startMin >= MIN_PER_HOUR){
            startHour++;
            startMin -= MIN_PER_HOUR;
        }

        endHour = startHour;
        endMin = startMin;

        if (endHour >= AM_PM_SWITCH){
            AM = false;
        }

        if (endHour > AM_PM_SWITCH){
            AM = false;
            endHour -= AM_PM_SWITCH;
        }

        return new int [] {endHour, endMin};
    }

    /**
     * Converts the time into a "XX:XX (a/p)n" formatted String.
     * @return formatted string for the end time.
     */
    @Override
    public String toString(){
        if (AM){ // am time
            if (endMin == CHECK_TIME_SINGLE_DIGIT){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + AM_LABEL;
            }
            return endHour + ":" + endMin + AM_LABEL;
        } else { // pm time
            if (endMin == CHECK_TIME_SINGLE_DIGIT){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + PM_LABEL;
            }
            return endHour + ":" + endMin + PM_LABEL;
        }
    }


}
