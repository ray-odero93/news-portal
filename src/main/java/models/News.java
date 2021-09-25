package models;

import java.sql.Timestamp;

public class News {
    private int id;
    private int userId;
    private String type;
    private  String content;
    private Timestamp postDate;

    public News(int id, int userId, String type, String content, Timestamp postDate) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.postDate = postDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
