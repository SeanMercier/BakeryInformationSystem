package com.example.assignment2finalversion;

public class Recipe {
    private String recname;
    private String recdescription;

    private int amount;

    public Recipe(String recname, String recdescription, int amount){
        this.recname = recname;
        this.recdescription = recdescription;
        this.amount = amount;
    }

    public String getRecname() {
        return recname;
    }

    public void setRecname(String recname) {
        this.recname = recname;
    }

    public String getRecdescription() {
        return recdescription;
    }

    public void setRecdescription(String recdescription) {
        this.recdescription = recdescription;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    @Override
    public String toString(){
        return  "Name: " + recname +
                "   Description: " + recdescription +
                "   Amount: " + amount +
                " calories";
    }
}
