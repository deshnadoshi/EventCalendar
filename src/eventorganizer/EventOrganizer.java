package eventorganizer;
import java.util.Scanner;

/**
 This class is the user interface class that processes the command lines.
 @author Haejin Song, Deshna Doshi
 */
public class EventOrganizer {
    private EventCalendar calendar;
    private static final int NUMBER_OF_PARAMS_IN_COMMAND_A = 7;
    private static final int NUMBER_OF_PARAMS_IN_COMMAND_R = 5;
    private static final int NUMBER_OF_PARAMS_IN_P_COMMANDS = 1;
    private static final int INITIAL_ARR_SIZE = 4;
    private static final int DATE_POSITION_IN_CMD = 1;
    private static final int DAY_POSITION_IN_DATE = 1;
    private static final int MONTH_POSITION_IN_DATE = 0;
    private static final int YEAR_POSITION_IN_DATE = 2;
    private static final int TIMESLOT_POSITION_IN_CMD = 2;
    private static final int LOC_POSITION_IN_CMD = 3;
    private static final int DEPT_POSITION_IN_CMD = 4;
    private static final int EMAIL_POSITION_IN_CMD = 5;
    private static final int DURATION_POSITION_IN_CMD = 6;



    /**
     Method that is continually listening in for commands that the user inputs.
     */
    public void run() {
        System.out.println("Event Organizer running...\n");
        int CMD_ITER = 1;
        Event[] events = new Event[INITIAL_ARR_SIZE];
        EventCalendar calendar = new EventCalendar(events, 0);
        Scanner currentCommand = new Scanner(System.in);
        boolean continueReading = true;
        while (continueReading) {
            continueReading = currentCommand.hasNextLine();
            if(CMD_ITER == 1){
                System.out.println();
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

    /**
     Processes the commands.
     A: add event
     R: remove event
     P: print list of events
     PE: print events based on date/timeslot order
     PC: print events based on campus/building order
     PD: print events based on department order
     @param command the string that is inputted
     @param calendar the current EventCalendar instance that is used
     @return true if it detected Q as a command, false otherwise.
     */
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
                        } else System.out.println("The event is already on the calendar.");
                    } counter += NUMBER_OF_PARAMS_IN_COMMAND_A;
                } else if (parsedCommand[counter].equals("R")) {
                    Event temp = makeEvent(parsedCommand, false);
                    if (checkDate(parsedCommand) && checkTimeslot(parsedCommand) && checkLocation(parsedCommand)){
                        if (calendar.remove(temp)) {
                            System.out.println("Event has been removed from the calendar!");
                        } else System.out.println("Cannot remove; event is not in the calendar!");
                    } counter += NUMBER_OF_PARAMS_IN_COMMAND_R;
                } else if (parsedCommand[counter].equals("P")) {
                    calendar.print();
                    counter += NUMBER_OF_PARAMS_IN_P_COMMANDS;
                } else if (parsedCommand[counter].equals("PE")) {
                    calendar.printByDate();
                    counter += NUMBER_OF_PARAMS_IN_P_COMMANDS;
                } else if (parsedCommand[counter].equals("PC")) {
                    calendar.printByCampus();
                    counter += NUMBER_OF_PARAMS_IN_P_COMMANDS;
                } else if (parsedCommand[counter].equals("PD")) {
                    calendar.printByDepartment();
                    counter += NUMBER_OF_PARAMS_IN_P_COMMANDS;
                } else return true;
            } else {
                if (!(parsedCommand[counter].equals(""))) {
                    System.out.println(parsedCommand[counter] + " is an invalid command!");
                } counter += NUMBER_OF_PARAMS_IN_P_COMMANDS;
            }
        } return false;
    }

    /**
     Makes an Event object with the given info.
     @param parsedCommand the info used to make the event
     @param full true if the event has all the info, false if the event only has date,
     timeslot, and location info, so the last two parameters are passed as nulls
     @return the Event object that is created
     */
    private Event makeEvent(String[] parsedCommand, boolean full) {
        if (full) {
            String[] parsedDate = parsedCommand[DATE_POSITION_IN_CMD].split("/");
            Date tempDate = new Date(Integer.parseInt(parsedDate[YEAR_POSITION_IN_DATE]), Integer.parseInt(parsedDate[MONTH_POSITION_IN_DATE]),
                    Integer.parseInt(parsedDate[DAY_POSITION_IN_DATE]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[TIMESLOT_POSITION_IN_CMD].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[LOC_POSITION_IN_CMD].toUpperCase());
            Contact tempContact = new Contact(parsedCommand[DEPT_POSITION_IN_CMD], parsedCommand[EMAIL_POSITION_IN_CMD]);
            return new Event(tempDate, tempTimeslot, tempLocation, tempContact,
                    Integer.parseInt(parsedCommand[DURATION_POSITION_IN_CMD]));
        } else {
            String[] parsedDate = parsedCommand[DATE_POSITION_IN_CMD].split("/");
            Date tempDate = new Date(Integer.parseInt(parsedDate[YEAR_POSITION_IN_DATE]), Integer.parseInt(parsedDate[MONTH_POSITION_IN_DATE]),
                    Integer.parseInt(parsedDate[DAY_POSITION_IN_DATE]));
            Timeslot tempTimeslot = Timeslot.valueOf(parsedCommand[TIMESLOT_POSITION_IN_CMD].toUpperCase());
            Location tempLocation = Location.valueOf(parsedCommand[LOC_POSITION_IN_CMD].toUpperCase());
            return new Event(tempDate, tempTimeslot, tempLocation, null, 0);
        }

    }

    /**
     Checks if the command is a valid one.
     @param commandLetter the string that holds the potential command
     @return true if commandLetter is a valid command, false otherwise.
     */
    private boolean checkValid(String commandLetter) {
        return (commandLetter.equals("A") || commandLetter.equals("R") | commandLetter.equals("P") ||
                commandLetter.equals("PE") || commandLetter.equals("PC") || commandLetter.equals("PD") ||
                commandLetter.equals("Q"));
    }

    /**
     Checks if it is a valid Timeslot.
     @param parsedCommand the full current command
     @return true if it is a valid Timeslot, false otherwise.
     */
    public boolean checkTimeslot(String[] parsedCommand){
        Timeslot timeslotObj = Timeslot.AFTERNOON;  // this is to use as a place holder for a timeslot object, bc we need to check if the timeslot is valid

        if (timeslotObj.isValid(parsedCommand[TIMESLOT_POSITION_IN_CMD])) {
            return true;
        }
        System.out.println("Invalid time slot!");
        return false;

    }

    /**
     Checks if it is a valid Location.
     @param parsedCommand the full current command
     @return true if it is a valid Location, false otherwise.
     */
    public boolean checkLocation(String[] parsedCommand){
        Location locationObj = Location.AB2225;

        if (locationObj.isValid(parsedCommand[LOC_POSITION_IN_CMD])) {
            return true;
        }
        System.out.println("Invalid location!");
        return false;
    }

    /**
     Checks if it is a valid Department.
     @param parsedCommand the full current command
     @return true if it is a valid Department, false otherwise.
     */
    public boolean checkDepartment(String[] parsedCommand){
        Department deptObj = Department.EE;

        if (deptObj.isValid(parsedCommand[DEPT_POSITION_IN_CMD])) {
            return true;
        }
        System.out.println("Invalid contact information!");
        return false;

    }

    /**
     Checks if it is a valid Date.
     @param parsedCommand the full current command
     @return true if it is a valid Date, false otherwise.
     */
    public boolean checkDate(String[] parsedCommand){
        String[] parsedDate = parsedCommand[DATE_POSITION_IN_CMD].split("/");
        Date tempDate = new Date(Integer.parseInt(parsedDate[YEAR_POSITION_IN_DATE]), Integer.parseInt(parsedDate[MONTH_POSITION_IN_DATE]),
                Integer.parseInt(parsedDate[DAY_POSITION_IN_DATE]));

        if (!tempDate.validCalendarDate()){
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

    /**
     Checks if it is a valid Duration for an event.
     Duration has to be less than 30 or greater than 120 to be a valid event.
     @param parsedCommand the full current command
     @return true if it is a valid Duration, false otherwise.
     */
    public boolean checkDuration(String[] parsedCommand){
        int duration = Integer.parseInt(parsedCommand[DURATION_POSITION_IN_CMD]);
        if (duration < 30 || duration > 120){
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
            return false;
        }
        return true;
    }

    /**
     Checks if it is a valid Contact.
     @param parsedCommand the full current command
     @return true if it is a valid Contact, false otherwise.
     */
    public boolean checkEmail(String[] parsedCommand){
        Contact emailCheckOnly = new Contact(Department.EE, parsedCommand[EMAIL_POSITION_IN_CMD]);
        if (!emailCheckOnly.emailCheck()){
            System.out.println("Invalid contact information!");
            return false;
        }
        return true;
    }
}
