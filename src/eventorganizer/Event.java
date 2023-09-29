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

    /**
     *
     * @param eventDate The date of the event being compared.
     * @param start The start time of the event being compared.
     * @param loc The location of the event being compared.
     * @return True if the events are the same, False otherwise.
     */
    public boolean equals(Date eventDate, Timeslot start, Location loc){
        return (this.date == eventDate && this.startTime == start && this.location == loc);
    }


    @Override
    public String toString(){
        EndTime endTime = new EndTime(startTime, duration);

        return "[Event Date: " + date.toString() + "] [Start Time: " + startTime.toString() + "] [End: " + endTime.toString() +"] @"
                + location.toString() + "(" + location.getBUILDING() + ", " + location.getCAMPUS() + ") ["
                + contact.getDepartment().getDEPARTMENTNAME() + ", " + contact.getEmail() + "]";
    }

    // @Override
    public boolean compareTo(Date eventDate, Timeslot startTime, int duration){
        return (this.date == eventDate && this.startTime == startTime);
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




}
