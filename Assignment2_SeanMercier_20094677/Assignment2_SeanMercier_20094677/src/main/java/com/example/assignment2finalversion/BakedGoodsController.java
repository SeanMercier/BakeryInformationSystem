package com.example.assignment2finalversion;


import com.example.assignment2finalversion.ADT.LinkList;
import com.example.assignment2finalversion.ADT.LinkNode;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.XStream;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BakedGoodsController {

    public TextField BakedGoodsField;
    public TextField CountryoforiginField;
    public ListView searchedbakedgoodsview;
    public ListView<BakedGood> BakedGoodslistView;
    public TextField DescriptionField;
    public TextField ImageField;
    public LinkList<BakedGood> BGList = BakedGoodsApplication.bakedgoods;

    public void addBakedGoods() {
        String bakedGoodID = BakedGoodsField.getText();
        String countryOfOrigin = CountryoforiginField.getText();
        String bgDescription = DescriptionField.getText();
        String imgURL = ImageField.getText();

        BakedGood bg1 = new BakedGood(bakedGoodID, countryOfOrigin, bgDescription, imgURL);
        BakedGoodsApplication.bakedgoods.addElement(bg1);
        System.out.println(BakedGoodsApplication.bakedgoods.head.contents);
        populateListView();
    }

    public void populateListView() {
        BakedGoodslistView.getItems().clear();
        BakedGoodsApplication.bakedgoods.addContentsToListView(BakedGoodslistView);
    }

    public void resetBakedGoods() {
        BakedGoodslistView.getItems().clear();
        deleteAllBakedGoods();
    }

    public void deleteBakedGood(ActionEvent actionEvent) {
        for (int i = 0; i < BGList.listRange(); i++){
            if(BakedGoodslistView.getSelectionModel().getSelectedIndex()==i) {
                BakedGoodslistView.getItems().remove(i);
            }
        }
    }

    public void deleteAllBakedGoods(){
        BakedGoodsApplication.bakedgoods.deleteEntireList();
    }


    public void updateBakedGood(ActionEvent actionEvent) {
        BakedGood selected = BakedGoodslistView.getSelectionModel().getSelectedItem();

        selected.setBgname(BakedGoodsField.getText());
        selected.setCountryoforigin(CountryoforiginField.getText());
        selected.setDescription(DescriptionField.getText());
        selected.setImageUrl(ImageField.getText());

        populateListView();
    }

    public void searchBakedGoodName(ActionEvent actionEvent) {
        LinkNode<BakedGood> BG= BakedGoodsApplication.bakedgoods.getHead();
        searchedbakedgoodsview.getItems().clear();

        while(BG != null) {
            if(BG.getContents().getBgname().contains(BakedGoodsField.getText())){
                String bgname;
                bgname = BG.getContents().getBgname();
                searchedbakedgoodsview.getItems().add(bgname);
            }
            BG = BG.next;
        }
    }

    public void searchBakedGoodDescription(ActionEvent actionEvent) {
        LinkNode<BakedGood> BG1= BakedGoodsApplication.bakedgoods.getHead();
        searchedbakedgoodsview.getItems().clear();

        while(BG1 != null) {
            if(BG1.getContents().getBgname().contains(DescriptionField.getText())){
                String bgdesc;
                bgdesc = BG1.getContents().getDescription();
                searchedbakedgoodsview.getItems().add(bgdesc);
            }
            BG1 = BG1.next;
        }
    }

    public void searchBakedGoodCountry(ActionEvent actionEvent) {
        LinkNode<BakedGood> BG2= BakedGoodsApplication.bakedgoods.getHead();
        searchedbakedgoodsview.getItems().clear();

        while(BG2 != null) {
            if(BG2.getContents().getBgname().contains(CountryoforiginField.getText())){
                String bgcoor;
                bgcoor = BG2.getContents().getCountryoforigin();
                searchedbakedgoodsview.getItems().add(bgcoor);
            }
            BG2 = BG2.next;
        }
    }

    public void loadBG(ActionEvent actionEvent) {
        try {
            load();
            populateListView();
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
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("bakedgoods.xml"));
        BakedGoodsApplication.bakedgoods = (LinkList<BakedGood>) in.readObject();
        in.close();
        populateListView();

    }

    public void saveBG(ActionEvent actionEvent) {
        try {
            save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void save() throws  Exception {
     //saves all baked goods
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileOutputStream("bakedgoods.xml"));
        LinkList<BakedGood> list1 = BakedGoodsApplication.bakedgoods;
        out.writeObject(list1);
        out.close();
    }
}