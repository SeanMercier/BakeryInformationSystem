package com.example.assignment2finalversion;

import com.example.assignment2finalversion.ADT.LinkList;
import com.example.assignment2finalversion.ADT.LinkNode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RecipeController {

    @FXML
    private TextField RecipeNameField;
    @FXML
    private TextField RecipeDescription;
    @FXML
    private TextField RecipeAmount;
    public ListView<Recipe> recipeListView;
    public ListView searchedrecipesview;

    public void addRecipe(){

        String RecipeNameR= RecipeNameField.getText();
        String RecipeDescriptionR = RecipeDescription.getText();
        int RecipeAmountR = Integer.valueOf(RecipeAmount.getText());

        Recipe recipeR = new Recipe(RecipeNameR, RecipeDescriptionR, RecipeAmountR);
        BakedGoodsApplication.recipes.addElement(recipeR);
        System.out.println(BakedGoodsApplication.recipes.head.contents);
        populateRecipesListView();
    }

    private void populateRecipesListView() {
        recipeListView.getItems().clear();
        BakedGoodsApplication.recipes.addContentsToListView(recipeListView);
    }

    public void deleteRecipe(ActionEvent actionEvent) {
        int i = 0;
        BakedGoodsApplication.recipes.deleteElement(i);
        populateRecipesListView();
    }

    public void deleteAllRecipes(){
        BakedGoodsApplication.recipes.deleteEntireList();
    }

    public void resetRecipe(ActionEvent actionEvent) {
        recipeListView.getItems().clear();
        deleteAllRecipes();
    }

    public void updateRecipe(ActionEvent actionEvent) {
        Recipe selected = recipeListView.getSelectionModel().getSelectedItem();

        selected.setRecname(RecipeNameField.getText());
        selected.setRecdescription(RecipeDescription.getText());
        selected.setAmount(Integer.valueOf(RecipeAmount.getText()));

        populateRecipesListView();
    }

    public void searchRecipeName(ActionEvent actionEvent) {
        LinkNode<Recipe> REC= BakedGoodsApplication.recipes.getHead();
        searchedrecipesview.getItems().clear();

        while(REC != null) {
            if(REC.getContents().getRecname().contains(RecipeNameField.getText())){
                String recname;
                recname = REC.getContents().getRecname();
                searchedrecipesview.getItems().add(recname);
            }
            REC = REC.next;
        }
    }

    public void searchRecipeDescription(ActionEvent actionEvent) {
        LinkNode<Recipe> REC1= BakedGoodsApplication.recipes.getHead();
        searchedrecipesview.getItems().clear();

        while(REC1 != null) {
            if(REC1.getContents().getRecdescription().contains(RecipeDescription.getText())){
                String recdesc;
                recdesc = REC1.getContents().getRecdescription();
                searchedrecipesview.getItems().add(recdesc);
            }
            REC1 = REC1.next;
        }
    }

    public void searchRecipeAmount(ActionEvent actionEvent) {
        LinkNode<Recipe> REC2= BakedGoodsApplication.recipes.getHead();
        searchedrecipesview.getItems().clear();

        while(REC2 != null) {
            if(REC2.getContents().getAmount().equals(RecipeAmount.getText())){
                String recam;
                recam = String.valueOf(REC2.getContents().getAmount());
                searchedrecipesview.getItems().add(recam);
            }
            REC2 = REC2.next;
        }
    }

    public void loadREC(ActionEvent actionEvent) {
        try {
            load();
            populateRecipesListView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void load() throws  Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{Recipe.class, BakedGood.class, Ingredients.class, LinkList.class, LinkNode.class};


        //setting up the XStream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);


        //saving the recipes to the recipes.xml save file
        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("recipes.xml"));
        BakedGoodsApplication.recipes = (LinkList<Recipe>) in.readObject();
        in.close();
        populateRecipesListView();

    }

    public void saveREC(ActionEvent actionEvent) {
        try {
            save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void save() throws  Exception {
        //saves all baked goods
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileOutputStream("recipes.xml"));
        LinkList<Recipe> list3 = BakedGoodsApplication.recipes;
        out.writeObject(list3);
        out.close();
    }
}
