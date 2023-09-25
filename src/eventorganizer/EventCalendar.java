package eventorganizer;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private int find(Event event) {
        return 0;
    } //search an event in the list
    private void grow() { //increase the capacity by 4
        int eventArrLength = events.length;
        final int GROWTH_CAPACITY = 4;

        Event [] growEventArr = new Event[events.length + GROWTH_CAPACITY];
        for (int i = 0; i < events.length; i++){
            growEventArr[i] = events[i];
        }

        events = null;
        events = growEventArr;
        System.out.println(events.length);

    }
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


    public static void main (String [] args){

    }
}
