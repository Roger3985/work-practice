-- 創建資料庫
create database workpractice_event;


-- 使用資料庫
use workpractice_event;


-- 會員表，並與部門表關聯
CREATE TABLE "user" (
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

COMMENT ON TABLE "user" IS '會員表';

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
DROP TABLE IF EXISTS "user";

-- 刪除 "department" 資料表
DROP TABLE IF EXISTS department;

-- 檢查表的型態跟內容
-- \d+ table name

-- QRTZ_LOCKS table
CREATE TABLE QRTZ_LOCKS
(
    SCHED_NAME VARCHAR(120) NOT NULL,
    LOCK_NAME  VARCHAR(40)  NOT NULL,
    PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);

-- QRTZ_JOB_DETAILS table
CREATE TABLE QRTZ_JOB_DETAILS
(
    SCHED_NAME      VARCHAR(120)  NOT NULL,
    JOB_NAME        VARCHAR(200)  NOT NULL,
    JOB_GROUP       VARCHAR(200)  NOT NULL,
    DESCRIPTION     VARCHAR(250)  NULL,
    JOB_CLASS_NAME  VARCHAR(250)  NOT NULL,
    IS_DURABLE      BOOLEAN       NOT NULL,
    IS_NONCONCURRENT BOOLEAN      NOT NULL,
    IS_UPDATE_DATA  BOOLEAN       NOT NULL,
    REQUESTS_RECOVERY BOOLEAN     NOT NULL,
    JOB_DATA        BYTEA         NULL,
    PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

-- More table creation statements as per the script...

