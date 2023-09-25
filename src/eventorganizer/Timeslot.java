package eventorganizer;

public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(2, 0),
    EVENING(6, 30);

    public final int HOUR;
    public final int MIN;

    Timeslot(int hour, int min){
        this.HOUR = hour;
        this.MIN = min;
    }

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
