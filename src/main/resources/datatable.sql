-- 創建資料庫
create database workpractice_event;


-- 使用資料庫
use workpractice_event;


-- 會員表
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(32),
    nickname VARCHAR(10) DEFAULT '',
    email VARCHAR(128) DEFAULT '',
    user_pic VARCHAR(128) DEFAULT '',
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL
);

COMMENT ON TABLE "user" IS '會員表';

-- 分類表
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(32) NOT NULL,
    category_alias VARCHAR(32) NOT NULL,
    create_user INT NOT NULL,
    create_time TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_category_user FOREIGN KEY (create_user) REFERENCES "user"(id)
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
    CONSTRAINT fk_article_user FOREIGN KEY (create_user) REFERENCES "user"(id)
);

COMMENT ON TABLE article IS '文章表';
