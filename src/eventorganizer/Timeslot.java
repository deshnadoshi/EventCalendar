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
        
        if (MORNING){ // am time
            if (MIN == 0){
                String startMinStr = "00";
                return HOUR + ":" + startMinStr + AM_LABEL;
            }
            return HOUR + ":" + MIN + AM_LABEL;
        } else { // pm time
            if (MIN == 0){
                String startMinStr = "00";
                return HOUR + ":" + startMinStr + PM_LABEL;
            }
            return HOUR + ":" + MIN + PM_LABEL;
        }
    }



}
