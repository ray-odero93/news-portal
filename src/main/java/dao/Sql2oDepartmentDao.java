package dao;

import models.Department;
import models.DepartmentNews;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.stream.Collectors;

public class Sql2oDepartmentDao implements DepartmentDao{
    private final Sql2o sql2o;
    private final Sql2oUserDao userDao;
    private final Sql2oNewsDao newsDao;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
        this.userDao = new Sql2oUserDao(sql2o);
        this.newsDao = new Sql2oNewsDao(sql2o);
    }

    @Override
    public void addDepartment(Department department) {
        String sql = "INSERT INTO departments(name, description) VALUES (:name, :description)";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        }
    }

    @Override
    public List<Department> getAllDepartments() {
        String sql = "SELECT * FROM departments";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<User> getDepartmentUsersById(int id) {
        return userDao.getAllUsers().stream()
                .filter(user -> user.getDepartmentId()==id)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentNews> getDepartmentNewsById(int id) {
        return newsDao.getDepartmentNews().stream()
                .filter(department -> department.getDepartmentId()==id)
                .collect(Collectors.toList());
    }

    @Override
    public Department findDepartmentById(int id) {
        String sql = "SELECT * FROM departments WHERE id=:id";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public void updateDepartment(Department department, String name, String description){
        String sql = "UPDATE departments SET (name, description) = (:name, :description)";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .executeUpdate();
            department.setName(name);
            department.setDescription(description);
        }
    }

    @Override
    public void clearAllDepartments() {
        String sql = "DELETE * FROM departments";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .executeUpdate();
        }
    }
}