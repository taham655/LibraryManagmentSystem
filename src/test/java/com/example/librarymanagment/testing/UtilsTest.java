package com.example.librarymanagment.testing;

import com.example.librarymanagment.model.Utils;
import javafx.scene.layout.FlowPane;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    public void testSearchBooks() {
        // Test searching for books with a valid name
        String name = "Harry ";
        FlowPane result = Utils.searchBooks(name);
        assertNotNull(result);
        assertEquals(FlowPane.class, result.getClass());


    }


    @Test
    void testisValidEmailAddress() {
        assertEquals(true, Utils.isValidEmailAddress("taham655@gmail.com"));
        assertEquals(false, Utils.isValidEmailAddress("taham6"));
        assertEquals(false, Utils.isValidEmailAddress("taham6@"));
    }

}