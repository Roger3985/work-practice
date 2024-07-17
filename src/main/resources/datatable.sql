-- 創建資料庫
create database workpractice_event;


-- 使用資料庫
use workpractice_event;


-- 會員表，並與部門表關聯
CREATE TABLE "userZKZK" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(60),
    nickname VARCHAR(10) DEFAULT '',
    email VARCHAR(128) DEFAULT '',
    user_pic BYTEA DEFAULT decode('', 'escape'),
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    department_id INT REFERENCES department(id)
);

COMMENT ON TABLE "userZKZK" IS '會員表';

-- 創建部門表
CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- 分類表
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(32) NOT NULL,
    category_alias VARCHAR(32) NOT NULL,
    create_user INT NOT NULL,
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_category_user FOREIGN KEY (create_user) REFERENCES "userZKZK"(id)
);

COMMENT ON TABLE category IS '分類表';

-- 文章表
CREATE TABLE article (
    id SERIAL PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(10000) NOT NULL,
    cover_img VARCHAR(128) NOT NULL,
    state VARCHAR(3) DEFAULT '草稿',
    category_id INT,
    create_user INT,
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_article_category FOREIGN KEY (category_id) REFERENCES category(id),
    CONSTRAINT fk_article_user FOREIGN KEY (create_user) REFERENCES "userZKZK"(id)
);

-- 刪除 "userZKZK" 資料表
DROP TABLE IF EXISTS "userZKZK";

-- 刪除 "department" 資料表
DROP TABLE IF EXISTS department;

-- 檢查表的型態跟內容
-- \d+ table name
