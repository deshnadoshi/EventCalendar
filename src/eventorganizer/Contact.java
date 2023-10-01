package eventorganizer;

/**
 Determines the validity of given contact information (department name and email).
 @author Deshna Doshi, Haejin Song
 */
public class Contact {
    private Department department;
    private String email;

    private static final int USERNAME_INDEX = 0;
    private static final int EXPECTED_STR_LENGTH = 2;
    private static final int EXTENSION_INDEX = 1;

    /**
     Constructor to initialize values of instance variables.
     @param department the Department object.
     @param email the email String.
     */
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    /**
     Constructor with Strings for the department name and email.
     @param departmentName the String of the department's name.
     @param email the email String.
     */
    public Contact(String departmentName, String email){
        if(departmentCheck(departmentName)){
            departmentName = departmentName.toUpperCase();

            for (Department checkDept : Department.values()){
                if(checkDept.toString().equals(departmentName)){
                    department = checkDept;
                }
            }
        }

        this.email = email;
    }

    /**
     Converts a valid department's name into a Department Enum.
     @param departmentName the department's name as a String.
     @return the Department object (converted from a String).
     */
    public Department convertStrToDept(String departmentName){
        if(departmentCheck(departmentName)){
            departmentName = departmentName.toUpperCase();

            for (Department checkDept : Department.values()){
                if(checkDept.toString().equals(departmentName)){
                    department = checkDept;
                }
            }
        }

        return department;
    }

    /**
     Determines if the department name is an acceptable name.
     @param departmentName the department's name as a String.
     @return true if the department name is an acceptable name, otherwise false.
     */
    public boolean departmentCheck(String departmentName){
        departmentName = departmentName.toLowerCase();
        String [] acceptableDeptName = {"cs", "ee" , "iti", "bait", "math"};
        for (int i = 0; i < acceptableDeptName.length; i++){
            if (acceptableDeptName[i].equals(departmentName)){
                return true;
            }
        }

        return false; 
    }

    /**
     Determines if the email is an acceptable email.
     @return true if the email has a valid format and is an acceptable email, false otherwise.
     */
    public boolean emailCheck(){
        email = email.toLowerCase();

        String [] emailArr = email.split("@", 0);
        String [] acceptableEmailUsername = {"cs", "ee" , "iti", "bait", "math"};
        String acceptableEmailExtension = "rutgers.edu";

        boolean acceptableUsername = false;
        boolean acceptableExtension = false;

        if (emailArr.length != EXPECTED_STR_LENGTH){
            return false;
        } else {
            for (int i = 0; i < acceptableEmailUsername.length; i++){
                if (emailArr[USERNAME_INDEX].equals(acceptableEmailUsername[i])){
                    acceptableUsername = true;
                }
            }
            if (emailArr[EXTENSION_INDEX].equals(acceptableEmailExtension)){
                acceptableExtension = true;
            }
        }

        if (acceptableUsername && acceptableExtension){
            return true;
        }

        return false;
    }

    /**
     Determines if the department and email are valid and if they match each other.
     @param deptName the department's name as a String.
     @return true if department and email are valid, and they match, otherwise false.
     */
    public boolean isValid(String deptName){

        if (!emailCheck()){
            // System.out.println("Invalid email.");
            return false;
        } else if (!departmentCheck(deptName)){
            // System.out.println("Invalid department.");
            return false;
        } else if (!match(deptName)){ // if they don't match, then you have to return false
            // System.out.println("Department name and email do not match.");
            return false;
        } else if (!emailCheck() && !departmentCheck(deptName)){
            // System.out.println("Invalid email and invalid department");
            return false;
        }

        return true;
    }

    /**
     Determines if the department name and the email correspond to each other.
     For example, is the department is CS and the email is bait@rutgers.edu, it is not acceptable.
     @param deptName the department's name as a String.
     @return true if the department name and email match, otherwise false.
     */
    public boolean match(String deptName){
        if (departmentCheck(deptName) && emailCheck()){ // if they match then the email first part should match the dept name
            String [] emailArr = email.toLowerCase().split("@", 0);


            if (deptName.equals(emailArr[USERNAME_INDEX])){
                return true; // if they are equal then that means it's the same department
            }
        }

        return false;
    }

    /**
     Getter for the email instance variable.
     @return the email String.
     */
    public String getEmail(){
        return email;
    }

    /**
     Getter for the department instance variable.
     @return the Department object.
     */
    public Department getDepartment() {
        return department;
    }

}
