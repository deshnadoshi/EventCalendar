package eventorganizer;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private int find(Event event) {
        for (int i = 0; i < this.events.length; i++) {
            if (this.events[i] == event) {
                return i;
            }
        }
        return -1;
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
        // Needs to be added: checking invalid timeslot? idk
        // isValid() in contact requires a string of the department name to be passed thru --deshna
        if (event.getDate().isValid() && event.getContact().isValid(event.getContact().getDepartment().toString()) && this.contains(event)) {
            if (this.numEvents >= this.events.length) {
                this.grow();
            }
            this.events[this.numEvents] = event; // might work
            this.numEvents += 1;
        }
        return false;
    }
    public boolean remove(Event event) {
        int removedEventIndex = find(event);
        for (int i = removedEventIndex; i < this.events.length - 1; i++) {
            this.events[i] = this.events[i+1];
        }
        this.events[this.numEvents] = null;
        this.numEvents -= 1;
        return false;
    }
    public boolean contains(Event event) {
        return false;
    }
    public void print() {
        System.out.println(events); // not complete (don't even know if this works)
    } //print the array as is
    public void printByDate() {
        Event[] dates = events;
        quickSort(dates, 0, events.length, "date");
        System.out.println(dates);
    } //ordered by date and timeslot
    public void printByCampus() {
        Event[] campuses = events;
        quickSort(campuses, 0, events.length, "campus");
        System.out.println(campuses);
    } //ordered by campus and building/room (but it is not doing building atm i have to figure it out)
    public void printByDepartment(){
        Event[] departments = events;
        quickSort(departments, 0, events.length, "department");
        System.out.println(departments);
    } //ordered by department

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
    // might see if i can make this shorter but idk if i can
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
