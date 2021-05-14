package com.example.covid_tracker;

public class StateModel {
    String state,active, recovered, dead, total;

    public StateModel(String state,String active, String recovered, String dead, String total) {
        this.active = active;
        this.state = state;
        this.recovered = recovered;
        this.dead = dead;
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public String getActive() {
        return active;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDead() {
        return dead;
    }

    public String getTotal() {
        return total;
    }
}
