package eventorganizer;
/**
 This class is the array-based implementation that holds the events.
 @author Haejin Song, Deshna Doshi
 */
public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    /**
     Constructor for EventCalendar
     @param events the Event list that holds all the events
     @param numEvents the number of events in the list
     */
    public EventCalendar(Event[] events, int numEvents) {
        this.events = new Event[0];
        this.numEvents = 0;
    }

    /**
     Finds index of the given event in the list.
     @param event the Event that is looked for in the list
     @return index of event if found, otherwise returns NOT_FOUND
     */
    private int find(Event event) {
        boolean emptyArr = true;
        for (int i = 0; i < this.numEvents; i++) {
            if (this.events[i].getDate().toString().equals(event.getDate().toString()) && this.events[i].getTimeSlot().equals(event.getTimeSlot()) &&
                    this.events[i].getLocation().equals(event.getLocation())) {
                return i;
            }
        }
        return NOT_FOUND;
    } //search an event in the list

    /**
     Increases size of the array when it is full
     */
    private void grow() { //increase the capacity by 4
        int eventArrLength = events.length;
        final int GROWTH_CAPACITY = 4;
        Event [] growEventArr = new Event[events.length + GROWTH_CAPACITY];
        for (int i = 0; i < events.length; i++){
            growEventArr[i] = events[i];
        }
        events = growEventArr;
    }

    /**
     Adds event to the list, unless it exists already.
     @param event the Event that is being added to the list
     @return true if it is successfully added, false otherwise
     */
    public boolean add(Event event) {
        // Needs to be added: checking invalid timeslot? idk
        // isValid() in contact requires a string of the department name to be passed thru --deshna
        if (event.getDate().advancedIsValid() && event.getContact().isValid(event.getContact().getDepartment().toString())
                && !this.contains(event)) {
            if (this.numEvents >= this.events.length) {
                this.grow();
            }
            this.events[this.numEvents] = event;
            this.numEvents += 1;
            return true;
        }
        return false;
    }

    /**
     Removes given event from the list, unless it is not found.
     @param event the Event that is being removed from the list
     @return true if it is successfully removed, false otherwise
     */
    public boolean remove(Event event) {
        int removedEventIndex = find(event);
        if (removedEventIndex == NOT_FOUND) return false;
        for (int i = removedEventIndex; i < this.numEvents - 1; i++) {
            this.events[i] = this.events[i+1];
        }
        this.events[this.numEvents] = null;
        this.numEvents -= 1;
        return true;
    }

    /**
     Checks if given event is in the list.
     @param event the Event that is looked for in the list
     @return true if it is in the list, false otherwise
     */
    public boolean contains(Event event) {
        if (find(event) == NOT_FOUND) return false;
        return true;
    }

    /**
     Prints out events as is.
     */
    public void print() {
        if (numEvents != 0) {
            System.out.println("* Event calendar *");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    }

    /**
     Prints events in date order. If the events share the same date, timeslot determines order.
     */
    public void printByDate() {
        if (numEvents != 0) {
            System.out.println("* Event calendar by event date and start time *");
            Event[] dates = arrayForSorting(events);
            quickSort(dates, 0, dates.length-1, "date");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(dates[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    }

    /**
     Prints events in campus order. If events share the same campus, room determines order.
     */
    public void printByCampus() {
        if (numEvents != 0) {
            System.out.println("* Event calendar by campus and building *");
            Event[] campuses = arrayForSorting(events);
            quickSort(campuses, 0, campuses.length-1, "campus");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(campuses[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }

    }

    /**
     Prints events in department order.
     */
    public void printByDepartment() {
        if (numEvents != 0) {
            System.out.println("* Event calendar by department *");
            Event[] departments = arrayForSorting(events);
            quickSort(departments, 0, departments.length-1, "department");
            for (int i = 0; i < numEvents; i++) {
                System.out.println(departments[i].toString());
            }
            System.out.println("* end of event calendar *");
        } else {
            System.out.println("Event calendar is empty!");
        }
    }

    /**
     Creates array with no nulls at the end.
     @param eventsArray the Event array that nulls are being removed from
     @return Event[] that has no nulls
     */
    public Event[] arrayForSorting(Event[] eventsArray) {
        Event[] temp = new Event[numEvents];
        for (int i = 0; i < numEvents; i++) {
            temp[i] = events[i];
        }
        return temp;
    }

    /**
     Implementation of Quicksort for print methods.
     @param unsortedArray the array that is being sorted
     @param low the first index of the part of the array being sorted
     @param high the last index of the part of the array being sorted
     @param howToSort determines how the array is being sorted
        date: sorted by date
        campus: sorted by campus and room
        department: sorted by department
     */
    public void quickSort(Event[] unsortedArray, int low, int high, String howToSort) {
        if (low >= high || low < 0) {
            return;
        }
        int pivot = partition(unsortedArray, low, high, howToSort);
        quickSort(unsortedArray, low, pivot - 1, howToSort);
        quickSort(unsortedArray, pivot + 1, high, howToSort);
    }

    /**
     Handles partitioning the array for Quicksort
     @param unsortedArray the array that is being sorted
     @param low the first index of the part of the array being sorted
     @param high the last index of the part of the array being sorted
     @param howToSort determines how the array is being sorted
     date: sorted by date
     campus: sorted by campus and room
     department: sorted by department
     @return temporary pivot value after partitioning
     */
    private int partition(Event[] unsortedArray, int low, int high, String howToSort) {
        Event pivot = unsortedArray[high];
        int temp_pivot = low - 1;
        if (howToSort.equals("date")) {
            for (int i = low; i < high; i++) {
                if (unsortedArray[i].getDate().compareTo(pivot.getDate()) < 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                } else if (unsortedArray[i].getDate().compareTo(pivot.getDate()) == 0 &&
                        (unsortedArray[i].compareTo(pivot) < 0 || unsortedArray[i].getDuration() < pivot.getDuration())) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            } temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        } else if (howToSort.equals("campus")) {
            for (int i = low; i < high; i++) {
                if (unsortedArray[i].getLocation().getCAMPUS().compareTo(pivot.getLocation().getCAMPUS()) < 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                } else if (unsortedArray[i].getLocation().getCAMPUS().compareTo(pivot.getLocation().getCAMPUS()) == 0 &&
                unsortedArray[i].getLocation().toString().compareTo(pivot.getLocation().toString()) < 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            } temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        } else if (howToSort.equals("department")) {
            for (int i = low; i < high; i++) {
                if ((unsortedArray[i].getContact().getDepartment().toString().compareTo(pivot.getContact().getDepartment().toString())) <= 0) {
                    temp_pivot += 1;
                    swap(i, temp_pivot, unsortedArray);
                }
            } temp_pivot += 1;
            swap(temp_pivot, high, unsortedArray);
            return temp_pivot;
        } return temp_pivot;
    }

    /**
     Handles swapping two array values for the Quicksort method.
     @param i first index to be swapped
     @param j second index to be swapped
     @param A the array that want the values in indices i and j to be swapped
     */
    private void swap(int i, int j, Event[] A) {
        Event temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }


}
