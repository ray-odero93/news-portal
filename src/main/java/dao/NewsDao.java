package dao;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {
//    create
    void addGeneralNews(News news);
    void addDepartmentNews(DepartmentNews departmentNews);

//    read
    List<News> getAllNews();
    List<News> getAllGeneralNews();
    List<DepartmentNews> getAllDepartmentNews();
    News findGeneralNewsById(int id);
    DepartmentNews findDepartmentNewsById(int id);

//    update
    void updateGeneralNews(News news, int userId, String content);
    void updateDepartmentNews(DepartmentNews departmentNews, int userId, String content, int departmentId);

//    delete
    void clearAllNews();
    void clearAllGeneralNews();
    void clearAllDepartmentNews();
}
