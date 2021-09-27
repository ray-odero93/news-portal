import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
import models.Department;
import models.DepartmentNews;
import models.News;
import models.User;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import static spark.Spark.*;

public class App {
    private static Sql2oUserDao userDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;
    private static Sql2o sql2o;
    private static URI dbUri;
    private static Logger logger = (Logger) LoggerFactory.getLogger(App.class);
    private static Gson gson = new Gson();
    private static Connection conn;

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();

        Integer port = (process.environment().get("PORT") != null) ?
                Integer.parseInt(process.environment().get("PORT")):7654;
        port(port);

        String connString = "jdbc:postgresql://localhost:5432/news_portal";

        try {
            if (System.getenv("DATABASE_URL") == null) {
                dbUri = new URI("postgres://localhost:5432/news_portal");
                sql2o = new Sql2o(connString, "moringa", "access");
            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));
                int dbport = dbUri.getPort();
                String host = dbUri.getHost();
                String path = dbUri.getPath();
                String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
                String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
                sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + dbport + path + username + password);
            }
        } catch (URISyntaxException ex) {
            logger.warning("Unable to connect to database.");
        }

        conn = sql2o.open();

        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);

        staticFileLocation("/public");

        get("/", (request, response) -> {
            response.redirect("layout.hbs");
            return null;
        }
        );

        get("/users", (req,res)-> gson.toJson(userDao.getAllUsers()));
        get("/departments", (req,res)-> gson.toJson(departmentDao.getDepartmentWithUserCount()));
        get("/users/:id",(req,res)->{
            int user_id = Integer.parseInt(req.params("id"));
            return gson.toJson(userDao.findUserById(user_id));
        });
        get("/departments/:id",(req,res)->{
            int dpt_id = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentDao.findDepartmentById(dpt_id));
        });
        get("/departments/:id/users",(req, res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentId.getDepartmentUsersById(departmentId));
        });
        get("/departments/:id/news",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            return gson.toJson(departmentId.getDepartmentNewsById(departmentId));
        });
        get("/news", (req,res)-> gson.toJson(newsDao.getAllNews()));
        get("/news/general", (req,res)-> gson.toJson(newsDao.getAllGeneralNews()));
        get("/news/department", (req,res)-> gson.toJson(newsDao.getAllDepartmentNews()));

        post("/Departments/new", "application/json", (req,res)->{
            Department department = gson.fromJson(req.body(),Department.class);

            departmentDao.addDepartment(department);
            res.status(201);
            res.type("application/json");
            res.redirect("/departments");
            return gson.toJson(department);
        });
        post("/Users/new", "application/json", (req,res)->{
            User user = gson.fromJson(req.body(), User.class);

            userDao.addUser(user);
            res.status(201);
            res.type("application/json");

            res.redirect("/users");
            return gson.toJson(user);
        });
        post("/News/new", "application/json", (req,res)->{
            News news = gson.fromJson(req.body(), News.class);

            newsDao.addGeneralNews(news);
            res.status(201);
            res.type("application/json");
            res.redirect("/news/general");
            return gson.toJson(news);
        });
        post("/DepartmentNews/new", "application/json", (req,res)->{
            DepartmentNews departmentNews = gson.fromJson(req.body(), DepartmentNews.class);

            newsDao.addDepartmentNews(departmentNews);
            res.status(201);
            res.type("application/json");

            res.redirect("/news/department");
            return gson.toJson(departmentNews);
        });

        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });
    }
}