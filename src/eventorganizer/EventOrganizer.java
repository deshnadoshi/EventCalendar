package eventorganizer;
import java.util.StringTokenizer;
import java.util.Scanner;

public class EventOrganizer {
    private EventCalendar calendar;
    public void run() {
        System.out.println("Event Organizer running...");
        Event[] events = new Event[0];
        EventCalendar calendar = new EventCalendar(events, 0);
        while (true) {
            Scanner currentCommand = new Scanner(System.in);
            String curCommand = currentCommand.nextLine();
            boolean stop = commandParser(curCommand, calendar);
            if (stop) {
                System.out.println("Event Organizer terminated.");
                break;
            }
        }
    }

    private boolean commandParser(String command, EventCalendar calendar) {
        String[] parsedCommand = command.split("\\s");
        int counter = 0;
        while (counter < parsedCommand.length) {
            if (checkValid(parsedCommand[counter])) {
                if (parsedCommand[counter].equals("A")) {
                    Event temp = makeEvent(parsedCommand, true);
                    if (calendar.add(temp)) {
                        System.out.println("Event added to the calendar.");
                    } else System.out.println("Event is invalid."); // change this.
                    counter += 7;
                } else if (parsedCommand[counter].equals("R")) {
                    Event temp = makeEvent(parsedCommand, false);
                    if (calendar.remove(temp)) {
                        System.out.println("Event has been removed from the calendar!");
                    } else System.out.println("Cannot remove; event is not in the calendar!");
                    counter += 5;
                } else if (parsedCommand[counter].equals("P")) {
                    calendar.print();
                    counter += 1;
                } else if (parsedCommand[counter].equals("PE")) {
                    calendar.printByDate();
                    counter += 1;
                } else if (parsedCommand[counter].equals("PC")) {
                    calendar.printByCampus();
                    counter += 1;
                } else if (parsedCommand[counter].equals("PD")) {
                    calendar.printByDepartment();
                    counter += 1;
                } else {
                    return true;
                }
            } else {
                System.out.println(parsedCommand[counter] + " is an invalid command!");
                counter += 1;
            }
        }
        return false;
    }

    private String[] deleteArray(String[] array, int numToRemove) {
        String[] newArray = new String[array.length-numToRemove];
        int counter = 0;
        for (int i = numToRemove; i < array.length; i++) {
            newArray[counter] = array[i];
            counter += 1;
        }
        return newArray;
    }

    private Event makeEvent(String[] parsedCommand, boolean full) {
        if (full) {
            String[] parsedDate = parsedCommand[1].split("/");
            Date tempDate = new Date(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1]),
                    Integer.parseInt(parsedDate[2]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[2].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[3].toUpperCase());
            Contact tempContact = new Contact(parsedCommand[4], parsedCommand[5]);
            return new Event(tempDate, tempTimeslot, tempLocation, tempContact,
                    Integer.parseInt(parsedCommand[6]));
        } else {
            String[] parsedDate = parsedCommand[1].split("/");
            Date tempDate = new Date(Integer.parseInt(parsedDate[0]), Integer.parseInt(parsedDate[1]),
                    Integer.parseInt(parsedDate[2]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[2].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[3].toUpperCase());
            return new Event(tempDate, tempTimeslot, tempLocation, null, 0);
        }

    }

    private boolean checkValid(String commandLetter) {
        return commandLetter.equals("A") || commandLetter.equals("R") | commandLetter.equals("P") ||
                commandLetter.equals("PE") || commandLetter.equals("PC") || commandLetter.equals("PD") ||
                commandLetter.equals("Q");
    }

}
