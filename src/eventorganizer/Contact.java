package eventorganizer;

public class Contact {
    private Department department;
    private String email;

    // email: cs@rutgers.edu, bait@rutgers.edu, iti@rutgers.edu
    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public boolean departmentCheck(){


        return false; 
    }

    public boolean emailCheck(){

        String [] emailArr = email.split("@");
        String [] acceptableEmailUsername = {"cs", "ee" , "iti", "bait", "math"};
        String acceptableEmailExtension = "rutgers.edu";

        // Delete print statement later
        for (int i = 0; i < emailArr.length; i++){
            System.out.println(emailArr[i] + "\n");
        }

        final int EXPECTED_STR_LENGTH = 2;
        // String is being split at the '@' symbol, so the expected number of elements in the String array is 2

        // DELETE THIS!
        System.out.println(emailArr.length + "len");


        if (emailArr.length != EXPECTED_STR_LENGTH){
            return false;
        }


        return true;
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
}
