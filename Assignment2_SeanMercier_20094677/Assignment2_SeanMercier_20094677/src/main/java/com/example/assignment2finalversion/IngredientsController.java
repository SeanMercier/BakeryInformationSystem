package com.example.assignment2finalversion;

import com.example.assignment2finalversion.ADT.LinkList;
import com.example.assignment2finalversion.ADT.LinkNode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IngredientsController {
    public Button AddIngredientsButton;
    public TextField ingNameField;
    public TextField ingDescriptionField;
    public TextField ingCaloriesField;
    public ListView<Ingredients> IngredientsListView;
    public ListView searchedingredientsview;


    public void addIngredients() {
        String Ingredients = ingNameField.getText();
        String Description = ingDescriptionField.getText();
        String Calories = ingCaloriesField.getText();


        Ingredients addI = new Ingredients( Ingredients, Description, Calories);
        BakedGoodsApplication.ingredients.addElement(addI);
        System.out.println(BakedGoodsApplication.ingredients.getHead());
        populateIngredientListView();
    }

    public void populateIngredientListView() {
        IngredientsListView.getItems().clear();
        BakedGoodsApplication.ingredients.addContentsToListView(IngredientsListView);
    }

    public void resetIngredients(ActionEvent actionEvent) {
        IngredientsListView.getItems().clear();
        deleteAllIngredients();
    }

    public void deleteIngredient(ActionEvent actionEvent) {
        int i = 0;
        BakedGoodsApplication.ingredients.deleteElement(i);
        populateIngredientListView();
    }

    private void deleteAllIngredients() {
        BakedGoodsApplication.ingredients.deleteEntireList();
    }

    public void searchIngredientName(ActionEvent actionEvent) {
        LinkNode<Ingredients> ING= BakedGoodsApplication.ingredients.getHead();
        searchedingredientsview.getItems().clear();

        while(ING != null) {
            if(ING.getContents().getIngname().contains(ingNameField.getText())){
                String ingname;
                ingname = ING.getContents().getIngname();
                searchedingredientsview.getItems().add(ingname);
            }
            ING = ING.next;
        }
    }

    public void searchIngredientDescription(ActionEvent actionEvent) {
        LinkNode<Ingredients> ING1= BakedGoodsApplication.ingredients.getHead();
        searchedingredientsview.getItems().clear();

        while(ING1 != null) {
            if(ING1.getContents().getDescription().contains(ingDescriptionField.getText())){
                String ingdesc;
                ingdesc = ING1.getContents().getDescription();
                searchedingredientsview.getItems().add(ingdesc);
            }
            ING1 = ING1.next;
        }
    }

    public void searchIngredientAmount(ActionEvent actionEvent) {
        LinkNode<Ingredients> ING2= BakedGoodsApplication.ingredients.getHead();
        searchedingredientsview.getItems().clear();

        while(ING2 != null) {
            if(ING2.getContents().getCalories().contains(ingCaloriesField.getText())){
                String bgcoor;
                bgcoor = ING2.getContents().getCalories();
                searchedingredientsview.getItems().add(bgcoor);
            }
            ING2 = ING2.next;
        }
    }

    public void updateIngredient(ActionEvent actionEvent) {
        Ingredients selected = IngredientsListView.getSelectionModel().getSelectedItem();

        selected.setIngname(ingNameField.getText());
        selected.setDescription(ingDescriptionField.getText());
        selected.setCalories(ingCaloriesField.getText());

        populateIngredientListView();
    }

    public void loadING(ActionEvent actionEvent) {
        try {
            load();
            populateIngredientListView();
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
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("ingredients.xml"));
        BakedGoodsApplication.ingredients = (LinkList<Ingredients>) in.readObject();
        in.close();
        populateIngredientListView();

    }

    public void saveING(ActionEvent actionEvent) {
        try {
            save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void save() throws  Exception {
        //saves all baked goods
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileOutputStream("ingredients.xml"));
        LinkList<Ingredients> list2 = BakedGoodsApplication.ingredients;
        out.writeObject(list2);
        out.close();
    }
}

