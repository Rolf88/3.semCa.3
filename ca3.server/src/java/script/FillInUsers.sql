INSERT INTO `User` (password, username) VALUES ('test','test');

SET @UserId = LAST_INSERT_ID();

INSERT INTO user_roles (user_id, roles) VALUES (@UserId,'User');
INSERT INTO user_roles (user_id, roles) VALUES (@UserId,'Admin');