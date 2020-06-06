package com.example.covid_19demo;

public class Cases {
    private String positive, recovery, deaths, state;

    public Cases(){

    }

    public Cases(String state, String positive, String recovery, String deaths) {
        this.positive = positive;
        this.recovery = recovery;
        this.deaths = deaths;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getRecovery() {
        return recovery;
    }

    public void setRecovery(String recovery) {
        this.recovery = recovery;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
