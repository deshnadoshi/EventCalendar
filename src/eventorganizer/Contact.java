package eventorganizer;

public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        this.email = email;
    }

    public boolean departmentCheck(){

        return false; 
    }

    public boolean emailCheck(){

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
}
