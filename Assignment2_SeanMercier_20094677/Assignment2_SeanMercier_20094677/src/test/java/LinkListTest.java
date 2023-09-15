import com.example.assignment2finalversion.ADT.LinkList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkListTest {
    private LinkList testList;
    @BeforeEach
    void setUp(){testList = new LinkList();}

    @Test
    void listRange() {
        testList.addElement(1);
        assertEquals(1,testList.listRange());
    }
    @Test
    void addElement() {
        testList.addElement(2);
        testList.addElement(3);
        testList.addElement(4);
        assertEquals(3, testList.listRange());
    }

    @Test
    void removeElements(){
        testList.addElement(1);
        testList.addElement(2);
        testList.addElement(3);
        testList.addElement(4);
        testList.deleteElement(0);
        assertEquals(3, testList.listRange());
    }

    @Test
    void getHead() {
        testList.addElement(1);
        testList.addElement(2);
        assertEquals(2, testList.getHead());
    }
/*
    @Test
    void addContentsToListView() {
        testList.addContentsToListView(new ListView<>());
        assertEquals(testList, testList.listRange());
    }
 */

}