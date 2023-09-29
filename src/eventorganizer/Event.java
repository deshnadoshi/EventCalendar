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
     * @param eventDate The date of the event being compared.
     * @param start The start time of the event being compared.
     * @param loc The location of the event being compared.
     * @return True if the events are the same, False otherwise.
     */
    public boolean equals(Date eventDate, Timeslot start, Location loc){
        return (this.date.equals(eventDate) && this.startTime.equals(start) && this.location.equals(loc));
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




}
