CREATE DATABASE news_portal;
\c news_portal;
CREATE TABLE users (id serial PRIMARY KEY, name VARCHAR, position VARCHAR, role VARCHAR, departmentId int);
CREATE TABLE department (id serial PRIMARY KEY, name VARCHAR, description VARCHAR);
CREATE TABLE news (id serial PRIMARY KEY, userId int, content VARCHAR, postDate timestamp, departmentId int, type VARCHAR);
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;