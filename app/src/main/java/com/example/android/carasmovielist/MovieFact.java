package com.example.android.carasmovielist;


public class MovieFact {
    private String mTitle;
    private String mDate;
    private String mLocation;
    private String mActor1;
    private String mActor2;
    private String mActor3;
    private String mDirector;
    private String mWriter;
    private String mProd_company;
    private String mDistributor;
    private String mFun_fact;


    public MovieFact(String title, String date, String location, String actor1, String actor2,
                     String actor3, String director, String writer, String prod_company,
                     String distributor, String fun_fact){
        mTitle = title;
        mDate = date;
        mLocation = location;
        mActor1 = actor1;
        mActor2 = actor2;
        mActor3 = actor3;
        mDirector = director;
        mWriter = writer;
        mProd_company = prod_company;
        mDistributor = distributor;
        mFun_fact = fun_fact;
    }

    public String getMovieTitle() {
        return mTitle;
    }

    public String getMovieDate() {
        return mDate;
    }

    public String getMovieLocation() {
        return mLocation;
    }

    public String getMovieActor1() {
        return mActor1;
    }

    public String getMovieActor2() {
        return mActor2;
    }

    public String getMovieActor3() {
        return mActor3;
    }

    public String getMovieDirector() {
        return mDirector;
    }

    public String getMovieWriter() {
        return mWriter;
    }

    public String getMovieProd_company() {
        return mProd_company;
    }

    public String getMovieDistributor() {
        return mDistributor;
    }

    public String getMovieFun_fact() {
        return mFun_fact;
    }
}
