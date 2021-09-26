package dao;

import com.sun.tools.javac.util.List;
import jdk.nashorn.internal.runtime.ListAdapter;
import models.DepartmentNews;
import models.News;

public interface NewsDao {
//    create
    void addGeneralNews(News news);
    void addDepartmentNews(DepartmentNews departmentNews);

//    read
    List<News> getAllNews();
    List<News> getGeneralNews();
    List<DepartmentNews> getDepartmentNews();

//    update
    void updateGeneralNews(News news, int userId, String content);
    void updateDepartmentNews(DepartmentNews departmentNews, int userId, String content, int departmentId);

//    delete
    void clearAllNews();
    void clearAllGeneralNews();
    void clearAllDepartmentNews();
}
