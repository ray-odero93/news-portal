package dao;

import models.User;

import java.util.List;

public interface UserDao {
//    create
    void addUser(User user);

//    read
    List<User> getAllUsers();
    User findUserById(int id);

//    update
    void updateUser(User user, String name, String position, String role, int departmentId);

//    delete
    void clearAllUsers();
}
