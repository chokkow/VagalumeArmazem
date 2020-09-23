package com.example.vagalumeex;

class BANDAS {
    private String title;
    private String band;


    public String getmus(){
        return title;
    }

    public String getband(){
        return band;
    }



    @Override
    public String toString(){
        return "Música:" + getmus()
                +"\nBandas:" +getband();

    }

}
