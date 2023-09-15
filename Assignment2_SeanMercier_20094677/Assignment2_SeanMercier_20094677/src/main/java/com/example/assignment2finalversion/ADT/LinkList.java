package com.example.assignment2finalversion.ADT;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;


import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

    public class LinkList<B> {
        public LinkNode<B> head = null;

        public int listRange() {
            LinkNode temp = head;
            int count = 0;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

        public String addElement(B a) {
            LinkNode<B> newNode = new LinkNode<>();
            newNode.setContents(a);
            newNode.next = head;
            head = newNode;

            return null;
        }

        public void deleteElement(int index) {
            index = listRange() - index - 1;
            if (index == 0) {
                head = head.next;
            } else {
                LinkNode live = head;
                for (int i = 0; i < index - 1; i++) {
                    live = live.next;
                }
                live.next = live.next.next;
            }
        }

        public void deleteEntireList(){
            head = null;
        }

        public boolean isEmpty(){
            if(listRange() <= 0){
                return false;
            } else {
                return true;
            }
        }

        public void addContentsToListView(ListView lv1) {
            LinkNode<B> temp = head;
            while(temp!=null){
                lv1.getItems().add(temp.getContents());
                temp = temp.next;
            }
        }

        public LinkNode<B> getHead(){
            return  head ;
        }

        public void addContentsToComboBox(ComboBox cb1){
            LinkNode<B> temp = null;
            while(temp!=null){
                cb1.getItems().add(temp.getContents());
                temp = temp.next;
            }
        }

        @Override
        public String toString() {
            return "LinkList{" +
                    "head=" + head +
                    '}';
        }
    }
