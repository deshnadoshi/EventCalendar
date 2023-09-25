package eventorganizer;

public class Event{
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

    // @Override
    public boolean equals(Date eventDate, Timeslot start, Location loc){

        return false;
    }


    @Override
    public String toString(){
        return "[Event Date: " + date.toString() + "] [Start Time: " + startTime.toString() + "] [End: " + "]";
    // in progress
    }

    // @Override
    public boolean compareTo(){

        return false;
    }




}
