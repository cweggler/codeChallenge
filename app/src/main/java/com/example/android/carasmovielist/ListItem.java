package com.example.android.carasmovielist;

public class ListItem {

    private String movieTitle;
    private String movieDate;
    private String movieLocation;

    public ListItem(String movieTitle, String movieDate, String movieLocation){
        this.movieTitle = movieTitle;
        this.movieDate = movieDate;
        this.movieLocation = movieLocation;

    }

    public String getTheTitle(){

        return movieTitle;
    }

    public String getTheDate(){

        return movieDate;
    }

    public String getTheLocation(){

        return movieLocation;
    }
}
