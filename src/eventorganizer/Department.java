package eventorganizer;

/**
 Declares all possible, valid Departments for an Event.
 @author Deshna Doshi, Haejin Song
 */
public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private final String DEPARTMENTNAME;

    /**
     * Constructor that initializes the instance variables.
     * @param departmentName the full name of the department.
     */
    Department(String departmentName){
        this.DEPARTMENTNAME = departmentName;
    }

    /**
     * Getter for the department name instance variable.
     * @return the name of the department.
     */
    public String getDEPARTMENTNAME() {
        return DEPARTMENTNAME;
    }

}
