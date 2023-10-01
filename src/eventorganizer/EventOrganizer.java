package eventorganizer;
import java.util.StringTokenizer;
import java.util.Scanner;

public class EventOrganizer {
    private EventCalendar calendar;
    public void run() {
        System.out.println("Event Organizer running...\n");
        int CMD_ITER = 1;
        Event[] events = new Event[4];
        EventCalendar calendar = new EventCalendar(events, 0);
        Scanner currentCommand = new Scanner(System.in);
        boolean continueReading = true;
        while (continueReading) {
            continueReading = currentCommand.hasNextLine();
            if(CMD_ITER == 1){
                System.out.println("");
                CMD_ITER++; // this is to print a line right before "a is an invalid command" otherwise it prints on the same line as the last command
            }
            String curCommand = currentCommand.nextLine();
            boolean stop = commandParser(curCommand, calendar);
            if (stop) {
                System.out.println("Event Organizer terminated.");
                break;
            }
        }
    }



    private boolean commandParser(String command, EventCalendar calendar) {
        String[] parsedCommand = command.split("\\s+");
        int counter = 0;
        while (counter < parsedCommand.length) {
            if (checkValid(parsedCommand[counter])) {
                if (parsedCommand[counter].equals("A")) {
                    if(checkDate(parsedCommand) && checkTimeslot(parsedCommand) && checkLocation(parsedCommand) && checkDepartment(parsedCommand)
                            && checkEmail(parsedCommand) && checkDuration(parsedCommand)) {
                        Event temp = makeEvent(parsedCommand, true);
                        if (calendar.add(temp)) {
                            System.out.println("Event added to the calendar.");
                        } else {
                            System.out.println("add failed"); // delete this later
                        }
                    }
                    counter += 7;
                } else if (parsedCommand[counter].equals("R")) {
                    Event temp = makeEvent(parsedCommand, false);
                    if (checkDate(parsedCommand) && checkTimeslot(parsedCommand) && checkLocation(parsedCommand) && checkDepartment(parsedCommand)
                            && checkEmail(parsedCommand) && checkDuration(parsedCommand)){
                        if (calendar.remove(temp)) {
                            System.out.println("Event has been removed from the calendar!");
                        } else System.out.println("Cannot remove; event is not in the calendar!");
                    }

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
                if (!(parsedCommand[counter].equals(""))) {
                    System.out.println(parsedCommand[counter] + " is an invalid command!");
                }
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
            Date tempDate = new Date(Integer.parseInt(parsedDate[2]), Integer.parseInt(parsedDate[0]),
                    Integer.parseInt(parsedDate[1]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[2].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[3].toUpperCase());
            Contact tempContact = new Contact(parsedCommand[4], parsedCommand[5]);
            return new Event(tempDate, tempTimeslot, tempLocation, tempContact,
                    Integer.parseInt(parsedCommand[6]));
        } else {
            String[] parsedDate = parsedCommand[1].split("/");
            Date tempDate = new Date(Integer.parseInt(parsedDate[2]), Integer.parseInt(parsedDate[0]),
                    Integer.parseInt(parsedDate[1]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[2].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[3].toUpperCase());
            return new Event(tempDate, tempTimeslot, tempLocation, null, 0);
        }

    }

    private boolean checkValid(String commandLetter) {
        return (commandLetter.equals("A") || commandLetter.equals("R") | commandLetter.equals("P") ||
                commandLetter.equals("PE") || commandLetter.equals("PC") || commandLetter.equals("PD") ||
                commandLetter.equals("Q"));
    }

    public boolean checkTimeslot(String [] parsedCommand){
        Timeslot timeslotObj = Timeslot.AFTERNOON;  // this is to use as a place holder for a timeslot object, bc we need to check if the timeslot is valid

        if(timeslotObj.isValid(parsedCommand[2])) {
            return true;
        }
        System.out.println("Invalid time slot!");
        return false;

    }

    public boolean checkLocation(String [] parsedCommand){
        Location locationObj = Location.AB2225;

        if(locationObj.isValid(parsedCommand[3])) {
            return true;
        }
        System.out.println("Invalid location!");
        return false;

    }

    public boolean checkDepartment(String [] parsedCommand){
        Department deptObj = Department.EE;

        if(deptObj.isValid(parsedCommand[4])) {
            return true;
        }
        System.out.println("Invalid contact information!");
        return false;

    }

    public boolean checkDate(String [] parsedCommand){
        String[] parsedDate = parsedCommand[1].split("/");
        Date tempDate = new Date(Integer.parseInt(parsedDate[2]), Integer.parseInt(parsedDate[0]),
                Integer.parseInt(parsedDate[1]));

        if(!tempDate.validCalendarDate()){
            System.out.println(tempDate.toString() + ": Invalid calendar date!");
            return false;
        } else if (!tempDate.isFutureDate()){
            System.out.println(tempDate.toString() + ": Event date must be a future date!");
            return false;
        } else if (!tempDate.withinSixMonths()){
            System.out.println(tempDate.toString() + ": Event date must be within 6 months!");
            return false;
        }
        return true;
    }

    public boolean checkDuration(String [] parsedCommand){
        int duration = Integer.parseInt(parsedCommand[6]);
        if (duration < 30 || duration > 120){
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
            return false;
        }

        return true;
    }

    public boolean checkEmail(String [] parsedCommand){
        Contact emailCheckOnly = new Contact(Department.EE, parsedCommand[5]);
        if (!emailCheckOnly.emailCheck()){
            System.out.println("Invalid contact information!");
            return false;
        }
        return true;
    }


}
