INSERT INTO `User` (password, username) VALUES ('test','1000:8abb5b9475c48697a75e9370b4c401b523ebbeb2d29f2b03:4f6ce16476f161a0eb60eac41efc2bbc5c395f076083d1e7');
-- sdlkjfsdlkjsfdljksdf

SET @UserId = LAST_INSERT_ID();

INSERT INTO user_roles (user_id, roles) VALUES (@UserId,'User');
INSERT INTO user_roles (user_id, roles) VALUES (@UserId,'Admin');