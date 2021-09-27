import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUserDao;
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
    }
}
