package com.example.assignment2finalversion;


import com.example.assignment2finalversion.ADT.LinkList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Hashtable;

public class BakedGoodsApplication extends Application {

    public static LinkList<BakedGood> bakedgoods;
    public static LinkList<Ingredients> ingredients;
    public static LinkList<Recipe> recipes;
    //Table to display baked goods information.
    private TableView<BakedGood> table;
    //TextFields for user input.
    private Text nameInput, typeInput, ingredientInput;
    //Hashtable to store baked goods information.
    private Hashtable<String, BakedGood> bakedGoodsHashtable;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        //Initialise baked goods table.
        bakedGoodsHashtable = new Hashtable<>();
        bakedgoods = new LinkList<>();
        ingredients = new LinkList<>();
        recipes = new LinkList<>();
        //Set up TableView columns.
        TableColumn<BakedGood, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BakedGood, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<BakedGood, String> ingredientsColumn = new TableColumn<>("Ingredients");
        ingredientsColumn.setMinWidth(300);
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("ingredients"));


        FXMLLoader fxmlLoader = new FXMLLoader(BakedGoodsApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        primaryStage.setTitle("Welcome to BestBaked!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

