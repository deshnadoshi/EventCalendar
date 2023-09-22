package eventorganizer;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private int find(Event event) {
        return 0;
    } //search an event in the list
    private void grow() { } //increase the capacity by 4
    public boolean add(Event event) {
        return false;
    }
    public boolean remove(Event event) {
        return false;
    }
    public boolean contains(Event event) {
        return false;
    }
    public void print() { } //print the array as is
    public void printByDate() { } //ordered by date and timeslot
    public void printByCampus() { } //ordered by campus and building/room
    public void printByDepartment(){ } //ordered by department
}
