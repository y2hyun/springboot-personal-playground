INSERT INTO user (name, password, email, create_date, update_date) VALUES
('Yang', 'password', 'y2hyun-test@test.com', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO board (title, sub_title, contents, board_type, create_date, update_date, user_id) VALUES 
('タイトル１', 'サブタイトル１', 'Hello１', 'free', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

INSERT INTO board (title, sub_title, contents, board_type, create_date, update_date, user_id) VALUES 
('タイトル２', 'サブタイトル２', 'Hello２', 'free', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);

INSERT INTO board (title, sub_title, contents, board_type, create_date, update_date, user_id) VALUES 
('タイトル３', 'サブタイトル３', 'Hello３', 'free', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1);
