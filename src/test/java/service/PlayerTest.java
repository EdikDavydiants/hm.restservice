package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {




    @Test
    void printInHTMLForm() {
        Player player = new Player(33, "Alex", "Aleksin", 2555);
        String htmlString = player.printInHTMLForm();

        String expected = "33   Alex Aleksin  2555<br>";
        assertEquals(expected, htmlString);
    }



}