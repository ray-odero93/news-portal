package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUserDao implements UserDao{
    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users(name, position, role, departmentId) VALUES (:name, :position, :role, :departmentId)";
        try(Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void updateUser(User user, String name, String position, String role, int departmentId){
        String sql = "UPDATE users SET (name, position, role, departmentId) = (:name, :position, :role, :departmentId) WHERE id = :id";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("position", position)
                    .addParameter("role", role)
                    .addParameter("departmentId", departmentId)
                    .addParameter("id", user.getId())
                    .executeUpdate();

            user.setName(name);
            user.setPosition(position);
            user.setRole(role);
            user.setDepartmentId(departmentId);
        }
    }

    @Override
    public void clearAllUsers() {
        String sql = "DELETE * FROM users";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql).executeUpdate();
        }
    }
}