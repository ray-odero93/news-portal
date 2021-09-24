package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void newUser_instantiatesCorrectly_true() {
        assertTrue(true);
    }

    @Test
    public void getName_instantiatesCorrectNameAttribute_string(){
        User testUser = new User("Ray", "Chief-Editor", "Oversee publication process from writing to printing.", 1);
        assertEquals("Ray", testUser.getName());
    }
}