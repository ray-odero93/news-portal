package models;

public class User {
    private int id;
    private  String position;
    private String name;
    private String role;
    private int departmentId;

    public User(String name, String position, String role, int departmentId) {
        this.name = name;
        this.position = position;
        this.role = role;
        this.departmentId = departmentId;
    }

    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return null;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return null;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return 0;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
