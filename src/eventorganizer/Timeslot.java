package eventorganizer;

/**
 Declares all possible, valid Timeslots for an Event.
 @author Deshna Doshi, Haejin Song
 */
public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(2, 0),
    EVENING(6, 30);
    public final int HOUR;
    public final int MIN;

    /**
     * Constructor that initializes the instance variables.
     * @param hour the hour of the start time.
     * @param min the minute of the start time.
     */
    Timeslot(int hour, int min){
        this.HOUR = hour;
        this.MIN = min;
    }

    /**
     * Converts the timeslot into a "XX:XX (a/p)m" formatted String
     * @return formatted String for the Timeslot.
     */
    public String toString(){
        
        if (HOUR == 10){ // am time
            if (MIN == 0){
                String startMinStr = "00";
                return HOUR + ":" + startMinStr + "am";
            }
            return HOUR + ":" + MIN + "am";
        } else { // pm time
            if (MIN == 0){
                String startMinStr = "00";
                return HOUR + ":" + startMinStr + "pm";
            }
            return HOUR + ":" + MIN + "pm";
        }
    }



}
