package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Test
    public void newNews_instantiatesCorrectly_true() {
        new News(1, 1, "entertainment", "jay z new album.");
        assertTrue(true);
    }

    @Test
    public void getType_returnsCorrectNameAttribute_string() {
        News news = new News(1, 1, "entertainment", "jay z new album.");
        assertEquals("entertainment", news.getType());
    }

    @Test
    public void getContent_returnsCorrectContentAttribute_string() {
        News news = new News(1,1, "entertainment", "jay z new album.");
        assertEquals("jay z new album.", news.getContent());
    }
}