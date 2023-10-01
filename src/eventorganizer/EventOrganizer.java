package eventorganizer;

public class EventOrganizer {
    public void run() {
        System.out.println("Event Organizer running...");
        Event[] events = new Event[0];
        EventCalendar calendar = new EventCalendar(events, 0);
        while (true) {
            String command = "";
            if (command.equals("Q")) {
                System.out.println("Event Organizer terminated.");
                break;
            }

        }

    }

}
