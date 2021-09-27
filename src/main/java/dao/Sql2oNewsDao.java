package dao;

import models.DepartmentNews;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao implements NewsDao{
    private final Sql2o sql2o;
    public static final String GENERAL_NEWS="general";
    public static final String DEPARTMENT_NEWS="department";

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void addGeneralNews(News news){
        String sql = "INSERT INTO news (userId, type, content, postDate) VALUES (:userId, :type, :content, now())";
        try (Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .addParameter("userId", news.getUserId())
                    .addParameter("type", news.getType())
                    .addParameter("content", news.getContent())
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        }
    }

    @Override
    public void addDepartmentNews(DepartmentNews departmentNews) {

    }

    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();
        news.addAll(getAllGeneralNews());
        news.addAll(getAllDepartmentNews());
        return news;
    }

    @Override
    public List<News> getAllGeneralNews() {
        String sql = "SELECT * FROM news WHERE type=:type";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", GENERAL_NEWS)
                    .executeAndFetch(News.class);
        }
    }

    public List<DepartmentNews> getAllDepartmentNews() {
        String sql = "SELECT * FROM news WHERE type = :type";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("type", DEPARTMENT_NEWS)
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public News findGeneralNewsById(int id) {
        String sql = "SELECT FROM news WHERE type = :type AND id = :id";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", GENERAL_NEWS)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    @Override
    public DepartmentNews findDepartmentNewsById(int id) {
        String sql = " SELECT * FROM news WHERE type=:type AND id=:id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .addParameter("id",id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public void updateGeneralNews(News news, int userId, String content) {
        String sql = "UPDATE news SET (userId, content) = (:userId, :content) WHERE id=:id ";
        try(Connection conn = sql2o.open()) {
            conn.createQuery(sql)
                    .addParameter("userId",userId)
                    .addParameter("content",content)
                    .addParameter("id",news.getId())
                    .executeUpdate();
            news.setUserId(userId);
            news.setContent(content);
        }

    }

    @Override
    public void updateDepartmentNews(DepartmentNews departmentNews, int userId, String content, int departmentId) {

    }

    @Override
    public void clearAllNews() {
        String sql="DELETE * FROM news";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public void clearAllGeneralNews() {
        String sql="DELETE * FROM news WHERE type = :type";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("type",GENERAL_NEWS)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAllDepartmentNews() {
        String sql="DELETE * FROM news WHERE type = :type";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("type",DEPARTMENT_NEWS)
                    .executeUpdate();
        }
    }
}