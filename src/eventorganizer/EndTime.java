package eventorganizer;

/**
 Calculates the end time of an Event based on the start time and duration of the Event.
 @author Deshna Doshi, Haejin Song
 */
public class EndTime {
    private Timeslot startTime;
    private int duration;
    private int endHour;
    private int endMin;
    private static final String AM_LABEL = "am";
    private static final String PM_LABEL = "pm";
    private static final int MIN_PER_HOUR = 60;
    private static final int AM_PM_SWITCH = 12;
    private static final int CHECK_TIME_SINGLE_DIGIT = 0;
    private static final int MIN_INDEX = 1;
    private static final int HOUR_INDEX = 0;
    private static final int SINGLE_DIGIT_TIME = 10;

    /**
     * Constructor that initializes the instance variables and determines the time group of the Event (AM vs. PM).
     * @param startTime the start time of the Event.
     * @param duration the duration of the Event in minutes.
     */
    public EndTime(Timeslot startTime, int duration){
        this.startTime = startTime;
        this.duration = duration;
        endHour = startTime.HOUR;
        endMin = startTime.MIN;
    }

    /**
     * Calculate the end time of the Event.
     * @return integer array with the ending hour (index 0) and ending minute (index 1) of the Event.
     */
    public int [] calcEndTime(){
        int durationCounter = duration;

        while (durationCounter >= MIN_PER_HOUR){
            durationCounter -= MIN_PER_HOUR; // decrease counter by the number of minutes in an hour
            endHour++;
        }
        int timeToNextHour = MIN_PER_HOUR - endMin;
        if (durationCounter >= timeToNextHour){
            durationCounter = durationCounter - timeToNextHour;
            endHour++;
            endMin = durationCounter;
        } else {
            endMin += durationCounter;
        }

        return new int [] {endHour, endMin};
    }

    /**
     * Converts the time into a "XX:XX (a/p)n" formatted String.
     * @return formatted string for the end time.
     */
    @Override
    public String toString(){
        int [] endTimes = calcEndTime();
        endMin = endTimes[MIN_INDEX];
        endHour = endTimes[HOUR_INDEX];
        if(endHour == AM_PM_SWITCH){
            if (endMin == CHECK_TIME_SINGLE_DIGIT){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + PM_LABEL;
            } if (endMin < SINGLE_DIGIT_TIME){
                String endMinStr = "0" + endMin;
                return endHour + ":" + endMinStr + PM_LABEL;
            }
            return endHour + ":" + endMin + PM_LABEL;
        }

        if (startTime.toString().contains("am")){ // am time
            if (endMin == CHECK_TIME_SINGLE_DIGIT){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + AM_LABEL;
            } if (endMin < SINGLE_DIGIT_TIME){
                String endMinStr = "0" + endMin;
                return endHour + ":" + endMinStr + AM_LABEL;
            }
            return endHour + ":" + endMin + AM_LABEL;
        } else { // pm time
            if (endMin == CHECK_TIME_SINGLE_DIGIT){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + PM_LABEL;
            } if (endMin < SINGLE_DIGIT_TIME){
                String endMinStr = "0" + endMin;
                return endHour + ":" + endMinStr + AM_LABEL;
            }
            return endHour + ":" + endMin + PM_LABEL;
        }
    }


}
