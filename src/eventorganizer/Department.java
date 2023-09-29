package eventorganizer;

public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private final String DEPARTMENTNAME;

    Department(String departmentName){
        this.DEPARTMENTNAME = departmentName;
    }

    public String getDEPARTMENTNAME() {
        return DEPARTMENTNAME;
    }

}
