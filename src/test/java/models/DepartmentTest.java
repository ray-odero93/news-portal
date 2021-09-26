package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Test
    public void getId() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        assertEquals(1, department.getId());
    }

    @Test
    public void setId() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        department.setId(2);
        assertEquals(2, department.getId());
    }

    @Test
    public void getName() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        assertEquals("publishing", department.getName());
    }

    @Test
    public void setName() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        department.setName("sunday funnies.");
        assertEquals("sunday funnies.", department.getName());
    }

    @Test
    public void getDescription() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        assertEquals("print stories, pieces.", department.getDescription());
    }

    @Test
    public void setDescription() {
        Department department = new Department(1,"publishing.", "print stories, pieces.");
        department.setDescription("print cartoons, comics.");
        assertEquals("print cartoons, comics.", department.getDescription());
    }
}