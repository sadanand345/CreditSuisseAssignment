package com.creditsuisseassignment.utilities;

public enum EventCons {

    STARTED("STARTED"),
    FINISHED("FINISHED");

    private final String value;

    EventCons(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    

}
    
}
