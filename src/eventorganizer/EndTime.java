package eventorganizer;

public class EndTime {
    // class to calculate the ending time for a given input timeslot and the duration timeslot
    private Timeslot startTime;
    private int duration;
    private int endHour;
    private int endMin;
    private final int TIME_PARAMETERS = 2;
    private final String AM_LABEL = "am";
    private final String PM_LABEL = "pm";
    private boolean AM = true;
    private final int MIN_PER_HOUR = 60;
    private final int AM_PM_SWITCH = 12;

    public EndTime(Timeslot startTime, int duration){
        this.startTime = startTime;
        this.duration = duration;
        if (startTime.toString().equals("AFTERNOON") || startTime.toString().equals("EVENING") ){
            AM = false;
        }

    }

    public int [] calcEndTime(){
        int startHour = startTime.HOUR;
        int startMin = startTime.MIN;

        while (duration >= 60){
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


    @Override
    public String toString(){
        if (AM){ // am time
            if (endMin == 0){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + AM_LABEL;
            }
            return endHour + ":" + endMin + AM_LABEL;
        } else { // pm time
            if (endMin == 0){
                String endMinStr = "00";
                return endHour + ":" + endMinStr + PM_LABEL;
            }
            return endHour + ":" + endMin + PM_LABEL;
        }
    }


    /**
    public static void main (String [] args){
        EndTime n = new EndTime(Timeslot.MORNING, 120);
        System.out.println(Timeslot.AFTERNOON.toString());
        int [] nEnd = n.calcEndTime();
        System.out.println(n.toString());

    }
     */





}
