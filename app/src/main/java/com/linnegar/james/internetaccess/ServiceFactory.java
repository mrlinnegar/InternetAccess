package com.linnegar.james.internetaccess;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceFactory {

    public static TrainService createService(JSONObject serviceData){

        try {
            String id = serviceData.getString("id");


            JSONObject origin = serviceData.getJSONObject("origin");
            JSONObject destination = serviceData.getJSONObject("destination");

            String originName = origin.getString("name");
            String originEtd = origin.getString("etd");
            String originStd = origin.getString("std");
            ServiceTime originTime = new ServiceTime(originStd, originEtd);

            String destinationName = destination.getString("name");
            String destinationSta = destination.getString("sta");
            String destinationEta = destination.getString("eta");
            ServiceTime destinationTime = new ServiceTime(destinationSta, destinationEta);

            Stop originStop = new Stop(originName, originTime);
            Stop destinationStop = new Stop(destinationName, destinationTime);

            TrainService train = new TrainService(id, originStop, destinationStop);

            return train;
        } catch (JSONException e) {
            return null;
        }
    }
}
