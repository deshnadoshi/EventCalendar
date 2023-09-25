package eventorganizer;

public class Date implements Comparable<Date>{

    private int year;
    private int month;
    private int day;

    public Date (int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isValid(){

        return false;
    }

    public boolean validCalendarDate(){

        return false;
    }
    public String toString(){
        return "";
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }
}
