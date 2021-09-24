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

    @Test
    public void getPosition_instantiatesCorrectPositionAttribute_string(){
        User testUser = new User("Ray", "Chief-Editor", "Oversee publication process from writing to printing.", 1);
        assertEquals("Chief-Editor", testUser.getPosition());
    }

    @Test
    public void getRole_instantiatesCorrectRoleAttribute_string() {
        User testUser = new User("Ray", "Chief-Editor", "Oversee publication process from writing to printing.", 1);
        assertEquals("Oversee publication process from writing to printing.", testUser.getRole());
    }

    @Test
    public void getDepartmentId_instantiatesCorrectDepartmentIdAttribute_integer() {
        User testUser = new User("Ray", "Chief-Editor", "Oversee publication process from writing to printing.", 1);
        assertEquals(1, testUser.getDepartmentId());
    }

    @Test
    public void getId_instantiatesCorrectIdAttributeOfClass_integer() {
        User testUser = new User("Ray", "Chief-Editor", "Oversee publication process from writing to printing.", 1);
        User anotherUser = new User("Purity", "Columnist", "Writing pieces for weekend columns.", 6);
        assertEquals(1, anotherUser.getId());
    }
}