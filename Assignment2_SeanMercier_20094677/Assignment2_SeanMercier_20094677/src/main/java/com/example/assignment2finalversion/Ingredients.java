package com.example.assignment2finalversion;

public class Ingredients {

    private String IngredientsField;
    private String DescriptionField;
    private String CaloriesField;

    public Ingredients(String ingname, String description, String calories){
        this.IngredientsField = ingname;
        this.DescriptionField = description;
        this.CaloriesField = calories;
    }

    public String getIngname() {
        return IngredientsField;
    }

    public void setIngname(String ingname) {
        this.IngredientsField = ingname;
    }

    public String getDescription() {
        return DescriptionField;
    }

    public void setDescription(String description) {
        this.DescriptionField = description;
    }

    public String getCalories() {
        return CaloriesField;
    }

    public void setCalories(String calories) {
        this.CaloriesField = calories;
    }

    @Override
    public String toString(){
        return "Name: " + IngredientsField +
                "   Description: " + DescriptionField+
                "   Calories: " + CaloriesField +
                "";
    }
}
