package com.linnegar.james.internetaccess;

public class TrainService {
    private String id;
    private Stop origin;
    private Stop destination;

    public TrainService(String id, Stop origin, Stop destination){
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public String toString(){
        return this.origin.toString();
    }
}
