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



}
