CREATE TABLE users (
  username    VARCHAR(50)  NOT NULL PRIMARY KEY,
  password    VARCHAR(255) NOT NULL,
  enabled     BOOLEAN      NOT NULL,
  email       VARCHAR(255),
  join_time   DATETIME     NOT NULL,
  avatar      VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  last_visit  DATETIME,
  credit      INT(11)      NOT NULL
)
  ENGINE = InnoDb;

CREATE TABLE topics (
  id          INT(11)     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(50) NOT NULL,
  description VARCHAR(255)
)
  ENGINE = InnoDb;

CREATE TABLE posts (
  id              INT(11)      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title           VARCHAR(255) NOT NULL,
  content         TEXT,
  editor          VARCHAR(50)  NOT NULL,
  create_time      DATETIME,
  modify_time     DATETIME,
  vote_count        INT(11),
  reply_count     INT(11),
  page_view       INT(11),
  topic_id        INT(11)      NOT NULL,
  last_reply_time DATETIME,
  FOREIGN KEY (editor) REFERENCES users (username),
  FOREIGN KEY (topic_id) REFERENCES topics (id)
)
  ENGINE = InnoDb;

CREATE TABLE replies (
  id          INT(11)     NOT NULL PRIMARY KEY AUTO_INCREMENT,
  post_id     INT(11),
  content     TEXT,
  editor      VARCHAR(50) NOT NULL,
  modify_time DATETIME,
  FOREIGN KEY (editor) REFERENCES users (username),
  FOREIGN KEY (post_id) REFERENCES posts (id)
)
  ENGINE = InnoDb;

CREATE TABLE tags (
  id          INT(11)     NOT NULL PRIMARY KEY,
  name        VARCHAR(50) NOT NULL,
  description VARCHAR(255)
)
  ENGINE = InnoDb;

CREATE TABLE tag_manager (
  post_id INT(11) NOT NULL,
  tag_id  INT(11) NOT NULL,
  FOREIGN KEY (post_id) REFERENCES posts (id),
  FOREIGN KEY (tag_id) REFERENCES tags (id)
)
  ENGINE = InnoDb;

CREATE TABLE authorities (
  username  VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users (username),
  UNIQUE INDEX authorities_idx_1 (username, authority)
)
  ENGINE = InnoDb;

CREATE TABLE groups (
  id         BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_name VARCHAR(50)     NOT NULL
)
  ENGINE = InnoDb;

CREATE TABLE group_authorities (
  group_id  BIGINT UNSIGNED NOT NULL,
  authority VARCHAR(50)     NOT NULL,
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  ENGINE = InnoDb;

CREATE TABLE group_members (
  id       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50)     NOT NULL,
  group_id BIGINT UNSIGNED NOT NULL,
  FOREIGN KEY (group_id) REFERENCES groups (id)
)
  ENGINE = InnoDb;