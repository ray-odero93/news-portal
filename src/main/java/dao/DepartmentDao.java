package dao;

import models.Department;
import models.DepartmentNews;
import models.User;

import java.util.List;

public interface DepartmentDao {
//    create
    void addDepartment(DepartmentDao department);

//    read
    List<Department> getAllDepartments();
    List<User> getDepartmentUsersById(int id);
    List<DepartmentNews> getDepartmentNewsById(int id);
    Department findDepartmentById(int id);

//    update
    void updateDepartment(Department department, String name, String description);

//    delete
    void clearAllDepartments();
}
