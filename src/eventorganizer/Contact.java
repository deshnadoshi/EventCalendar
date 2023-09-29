package eventorganizer;

public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public Contact(String departmentName, String email){
        if(departmentCheck(departmentName)){
            departmentName = departmentName.toUpperCase();

            for (Department checkDept : Department.values()){
                if(checkDept.toString().equals(departmentName)){
                    department = checkDept;
                }
            }
        }
    }

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

    public boolean match(String deptName){
        if (departmentCheck(deptName) && emailCheck()){ // if they match then the email first part should match the dept name
            String [] emailArr = email.toLowerCase().split("@", 0);
            final int USERNAME_INDEX = 0;

            if (deptName.equals(emailArr[USERNAME_INDEX])){
                return true; // if they are equal then that means it's the same department
            }
        }

        return false;
    }

    public String getEmail(){
        return email;
    }

    public Department getDepartment() {
        return department;
    }

}
