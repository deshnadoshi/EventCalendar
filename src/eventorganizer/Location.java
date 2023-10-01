package eventorganizer;

/**
 Declares all possible, valid Locations for an Event.
 @author Deshna Doshi, Haejin Song
 */
public enum Location {
    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");
    private final String BUILDING;
    private final String CAMPUS;

    /**
     * Constructor that initializes the instance variables.
     * @param building the full name of the building.
     * @param campus the full name of the campus.
     */
    Location(String building, String campus) {
        this.BUILDING = building;
        this.CAMPUS = campus;
    }

    /**
     * Getter for the building instance variable.
     * @return the name of the building.
     */
    public String getBUILDING() {
        return BUILDING;
    }

    /**
     * Getter for the campus instance variable.
     * @return the name of the campus.
     */
    public String getCAMPUS() {
        return CAMPUS;
    }

    /**
     * Determines if a given location is valid.
     * @param locationName String of the location's name.
     * @return true if the location is valid, otherwise false.
     */
    public boolean isValid(String locationName){
        locationName = locationName.toLowerCase();
        String [] acceptableLocation = {"hll114", "arc103", "be_aud", "til232", "ab2225", "mu302"};
        for (int i = 0; i < acceptableLocation.length; i++){
            if(acceptableLocation[i].equals(locationName)){
                return true;
            }
        }
        return false;
    }
}
