package com.linnegar.james.internetaccess;

public class Stop {
    private String name;
    private ServiceTime time;

    public Stop(String newName, ServiceTime time){
        name = newName;
        this.time = time;
    }

    public String toString(){
        return name + " " + time.getEstimated() + " " + time.getScheduled() ;
    }
}
