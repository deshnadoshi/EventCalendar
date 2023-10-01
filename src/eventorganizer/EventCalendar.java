package eventorganizer;

public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    public EventCalendar(Event[] events, int numEvents) {
        this.events = new Event[0];
        this.numEvents = 0;
    }

    private int find(Event event) {
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i].getDate().equals(event.getDate()) && this.events[i].getTimeSlot().equals(event.getTimeSlot()) &&
                this.events[i].getLocation().equals(event.getLocation())) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search an event in the list

    private void grow() { //increase the capacity by 4
        int eventArrLength = events.length;
        final int GROWTH_CAPACITY = 4;

        Event [] growEventArr = new Event[events.length + GROWTH_CAPACITY];
        for (int i = 0; i < events.length; i++){
            growEventArr[i] = events[i];
        }
        events = growEventArr;
        System.out.println(events.length);
    }
    public boolean add(Event event) {
        // Needs to be added: checking invalid timeslot? idk
        // isValid() in contact requires a string of the department name to be passed thru --deshna
        if (event.getDate().isValid() && event.getContact().isValid(event.getContact().getDepartment().toString()) && !this.contains(event)) {
            if (this.numEvents >= this.events.length) {
                this.grow();
            }
            this.events[this.numEvents] = event;
            this.numEvents += 1;
            return true;
        }
        return false;
    }
    public boolean remove(Event event) {
        int removedEventIndex = find(event);
        if (removedEventIndex == NOT_FOUND) return false;
        for (int i = removedEventIndex; i < this.events.length - 1; i++) {
            this.events[i] = this.events[i+1];
        }
        this.events[this.numEvents] = null;
        this.numEvents -= 1;
        return true;
    }
    public boolean contains(Event event) {
        if (find(event) == NOT_FOUND) return false;
        return true;
    }
    public void print() {
        if (numEvents != 0) {
            System.out.println("* Event calendar *");
            for (int i = 0; i < events.length; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    } //print the array as is
    public void printByDate() {
        if (numEvents != 0) {
            System.out.println("* Event calendar *");
            Event[] dates = events;
            quickSort(dates, 0, events.length, "date");
            for (int i = 0; i < dates.length; i++) {
                System.out.println(dates[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    } //ordered by date and timeslot
    public void printByCampus() {
        if (numEvents != 0) {
            System.out.println("* Event calendar by campus and building *");
            Event[] campuses = events;
            quickSort(campuses, 0, events.length, "campus");
            for (int i = 0; i < campuses.length; i++) {
                System.out.println(campuses[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    } //ordered by campus and building/room (but it is not doing building atm i have to figure it out)
    public void printByDepartment(){
        if (numEvents != 0) {
            System.out.println("* Event calendar by department *");
            Event[] departments = events;
            quickSort(departments, 0, events.length, "department");
            for (int i = 0; i < departments.length; i++) {
                System.out.println(departments[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }
    } //ordered by department

    public int getNumEvents() {
        return numEvents;
    }

    // Sorting algorithm (Quicksort)
    public void quickSort(Event[] unsortedArray, int low, int high, String howToSort) {
        if (low >= high || low < 0) {
            return;
        }
        int pivot = partition(unsortedArray, low, high, howToSort);
        quickSort(unsortedArray, low, pivot - 1, howToSort);
        quickSort(unsortedArray, pivot + 1, high, howToSort);
    }
    // howToSort = date, campus, department
    private int partition(Event[] unsortedArray, int low, int high, String howToSort) {
        Event pivot = unsortedArray[high];
        int temp_pivot = low - 1;
        if (howToSort.equals("date")) {
            for (int i = low; i < high - 1; i++) {
                if (unsortedArray[i].getDate().toString().compareTo(pivot.getDate().toString()) <= 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            }
            temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        } else if (howToSort.equals("campus")) {
            for (int i = low; i < high - 1; i++) {
                if (unsortedArray[i].getLocation().getCAMPUS().compareTo(pivot.getLocation().getCAMPUS()) <= 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            }
            temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        } else if (howToSort.equals("department")) {
            for (int i = low; i < high - 1; i++) {
                if ((unsortedArray[i].getContact().getDepartment().compareTo(pivot.getContact().getDepartment())) <= 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            }
            temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        }
        return temp_pivot;
    }

    private void swap(int i, int j, Event[] A) {
        Event temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


}
