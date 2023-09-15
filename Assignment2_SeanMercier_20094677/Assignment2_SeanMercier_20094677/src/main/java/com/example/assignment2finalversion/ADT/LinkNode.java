package com.example.assignment2finalversion.ADT;

public class LinkNode<N> {
    public LinkNode<N> next = null;

    public N contents;

    public N getContents(){
        return contents;
    }

    public void setContents(N con) {
        contents = con;
    }

}
