package com.linnegar.james.internetaccess;

public class ServiceTime {
    private String scheduled;
    private String estimated;

    public ServiceTime(String scheduled, String estimated){
        this.scheduled = scheduled;
        this.estimated = estimated;
    }

    public String getEstimated() {
        return this.estimated;
    }

    public String getScheduled() {
        return this.scheduled;
    }
}
