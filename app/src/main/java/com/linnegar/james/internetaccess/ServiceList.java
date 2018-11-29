package com.linnegar.james.internetaccess;


import java.util.ArrayList;


public class ServiceList {
    private ArrayList<TrainService> services;

    public ServiceList(){
        this.services = new ArrayList<TrainService>();
    }

    public void addService(TrainService service){
        services.add(service);
    }

    public void clear(){
        services.clear();
    }

    public String toString() {
        StringBuffer output = new StringBuffer();

        for(int i = 0; i < this.services.size(); i++){
            output.append(services.get(i).toString());
            output.append('\n');
        }

        return output.toString();
    }

}
