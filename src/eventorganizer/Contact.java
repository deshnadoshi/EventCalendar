package eventorganizer;

public class Contact {
    private Department department;
    private String email;
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public boolean departmentCheck(){
        String userInputDepartment = department.toString().toLowerCase();
        String [] acceptableDeptName = {"cs", "ee" , "iti", "bait", "math"};

        for (int i = 0; i < acceptableDeptName.length; i++){
            if (acceptableDeptName[i].equals(userInputDepartment)){
                return true;
            }
        }

        return false; 
    }

    public boolean emailCheck(){
        email = email.toLowerCase();

        String [] emailArr = email.split("@", 0);
        String [] acceptableEmailUsername = {"cs", "ee" , "iti", "bait", "math"};
        String acceptableEmailExtension = "rutgers.edu";

        final int EXPECTED_STR_LENGTH = 2;
        final int USERNAME_INDEX = 0;
        final int EXTENSION_INDEX = 1;

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

    public boolean isValid(){

        if (!emailCheck()){
            System.out.println("Invalid email.");
            return false;
        } else if (!departmentCheck()){
            System.out.println("Invalid department.");
            return false;
        } else if (!emailCheck() && !departmentCheck()){
            System.out.println("Invalid email and invalid department");
            return false;
        }

        return true;
    }

    // Need to delete this later: using FOR TESITNG ONLY --deshna
    public static void main(String[] args) {
        Contact c = new Contact(Department.EE, "cs@rutgers.edu");
        System.out.println(c.departmentCheck());

    }
}

// ALSO NEED TO CHECK IF DEPARTMENT AND EMAIL MATCH !!
