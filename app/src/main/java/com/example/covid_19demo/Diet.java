package com.example.covid_19demo;

public class Diet {
    private String breakfast, lunch, snack, dinner;

    public Diet(){

    }

    public Diet(String breakfast, String lunch, String snack, String dinner) {
        this.breakfast = breakfast; // can you please add more information
        this.lunch = lunch;
        this.snack = snack;
        this.dinner = dinner;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void getBreakfast(String breakfast) {
    }
}
