package com.example.assignment2finalversion;


import com.example.assignment2finalversion.ADT.LinkList;

import java.util.HashMap;

public class BakedGood {

    private String bgname;
    private String countryoforigin;
    private String description;
    private String imageUrl;
    private HashMap<String,BakedGood> bkgds;
    private String searchbgname;
    private String countryorigin;
    private String bgdescription;
    private String imageurl;

    public BakedGood(String bgname, String description, String countryoforigin, String imageUrl){
        this.bgname = bgname;
        this.countryoforigin = countryoforigin;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    public String getBgname() {
        return bgname;
    }

    public void setBgname(String bgname) {
        this.bgname = bgname;
    }

    public String getCountryoforigin() {
        return countryoforigin;
    }

    public void setCountryoforigin(String countryoforigin) {
        this.countryoforigin = countryoforigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString(){
        return  "Name: " + bgname +
                "   Country of origin: " + countryoforigin +
                "   Description: " + description +
                "   Image URL: " + imageUrl +
                "";
    }
}
