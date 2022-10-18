-- Sample data is loaded when application starts up.

INSERT INTO referee (id, referee_name) VALUES
                                           (1, 'Sjoerd'),
                                           (2, 'Denis'),
                                           (3, 'Matthijs'),
                                           (4, 'Gerard'),
                                           (5, 'Michael'),
                                           (6, 'Jorgen'),
                                           (7, 'Daan'),
                                           (8, 'Teo'),
                                           (9, 'Hans');

INSERT INTO team (id, team_name) VALUES
                                     (1, 'Toppers'),
                                     (2, 'FC Twente'),
                                     (3, 'FC Barcelona'),
                                     (4, 'Juventus'),
                                     (5, 'Bayern Munchen'),
                                     (6, 'Dortmund');

INSERT INTO player (player_id, player_name, gender, team_id) VALUES
                                                       (1, 'Yannick', 'MALE', 1),
                                                       (2, 'Stan', 'MALE', 2),
                                                       (3, 'Erik', 'MALE', 3),
                                                       (4, 'Peter', 'MALE', 4),
                                                       (5, 'Sjoerd', 'MALE', 1),
                                                       (6, 'Noah', 'MALE', 2),
                                                       (7, 'Sem', 'MALE', 3),
                                                       (8, 'Liam', 'MALE', 4),
                                                       (9, 'Lucas', 'MALE', 1),
                                                       (10, 'Daan', 'MALE', 2),
                                                       (11, 'Finn', 'MALE', 3),
                                                       (12, 'Levi', 'MALE', 4),
                                                       (13, 'Luuk', 'MALE', 1),
                                                       (14, 'Mees', 'MALE', 2),
                                                       (15, 'James', 'MALE', 3),
                                                       (16, 'Milan', 'MALE', 4),
                                                       (17, 'Sam', 'MALE', 1),
                                                       (18, 'Noud', 'MALE', 2),
                                                       (19, 'Benjamin', 'MALE', 3),
                                                       (20, 'Luca', 'MALE', 4),
                                                       (21, 'Bram', 'MALE', 1),
                                                       (22, 'Mason', 'MALE', 2),
                                                       (23, 'Max', 'MALE', 3),
                                                       (24, 'Thomas', 'MALE', 4),
                                                       (25, 'Adam', 'MALE', 1),
                                                       (26, 'Hugo', 'MALE', 2),
                                                       (27, 'Jesse', 'MALE', 3),
                                                       (28, 'Boaz', 'MALE', 4),
                                                       (29, 'Olivier', 'MALE', 1),
                                                       (30, 'Teun', 'MALE', 2),
                                                       (31, 'Julian', 'MALE', 3),
                                                       (32, 'Lars', 'MALE', 4),
                                                       (33, 'Thijs', 'MALE', 1),
                                                       (34, 'Gijs', 'MALE', 2),
                                                       (35, 'Siem', 'MALE', 3),
                                                       (36, 'Guus', 'MALE', 4),
                                                       (37, 'Mats', 'MALE', 1),
                                                       (38, 'Zayn', 'MALE', 2),
                                                       (39, 'Otis', 'MALE', 3),
                                                       (40, 'Jeans', 'MALE', 4),
                                                       (41, 'Jack', 'MALE', 1),
                                                       (42, 'Floris', 'MALE', 2),
                                                       (43, 'Ties', 'MALE', 3),
                                                       (44, 'Joep', 'MALE', 4);

INSERT INTO trainer (id, trainer_name, house_number, team_id) VALUES
                           (1, 'Jorgen', 55, 1),
                           (2, 'Michael', 102, 2),
                           (3, 'Rutger', 2, 3),
                           (4, 'Wouter', 1005, 4),
                           (5, 'Rene', 38, 5),
                           (6, 'Harry', 29, null),
                           (7, 'Peter', 47, null),
                           (8, 'Vincent', 98, null);

INSERT INTO training (id, date, team_id) VALUES
                            (1, '2022-10-10', 1),
                            (2, '2022-10-12', 1),
                            (3, '2022-10-13', 2),
                            (4, '2022-10-15', 3),
                            (5, '2022-11-10', 4),
                            (6, '2022-11-8', 4),
                            (7, '2022-11-6', 3),
                            (8, '2022-11-5', 2);

INSERT INTO injury (id, start_date, end_date, explanation, player_id) VALUES
                                            (1, '2021-10-28', '2022-2-8', 'Knee injury', 4),
                                            (2, '2019-6-22', '2021-5-11', 'Ankle sprain', 6),
                                            (3, '2022-9-18', null, 'Groin strain', 7),
                                            (4, '2022-1-13', null, 'Wrist injury', 9),
                                            (5, '2022-5-8', null, 'concussion', 41),
                                            (6, '2021-2-6', '2021-4-13', 'Acromioclavicular sprain', 28),
                                            (7, '2021-2-7', '2021-6-16', 'Right knee injury', 17),
                                            (8, '2022-3-7', '2022-1-2', 'Shoulder dislocation', 33),
                                            (9, '2020-12-22', '2021-3-26', 'Hip pointer', 11),
                                            (10, '2020-11-27', null, 'Hip injury', 3),
                                            (11, '2018-10-17', '2019-3-15', 'hamstring', 29);

INSERT INTO game (id, date, opponent, referee_id, team_id) VALUES
                                    (1, '2021-10-28', 'FC Tukkers', 2, 1),
                                    (2, '2021-10-28', 'Real Madrid', 1, 2),
                                    (3, '2021-10-28', 'Liverpool FC', 8, 3),
                                    (4, '2021-10-28', 'Napoli', 7, 4),
                                    (5, '2021-10-28', 'Paris Saint-Germain', 5, 1),
                                    (6, '2021-10-28', 'AC Milan', 1, 2),
                                    (7, '2021-10-28', 'Ajax Amsterdam', 2, 3),
                                    (8, '2021-10-28', 'Inter Milan', 4, 4),
                                    (9, '2021-10-28', 'Benfica', 6, 1),
                                    (10, '2021-10-28', 'Tottenham Hotspur', 7, 2),
                                    (11, '2021-10-28', 'Real Betis', 5, 3),
                                    (12, '2021-10-28', 'Sporting', 2, 4);

INSERT INTO player_data(id, player_id, height, weight, top_speed, prefered_foot, position, work_rate, weak_foot, skill_moves, dribbling, shooting, passing) VALUES
(1, 1, 188, 75.0, 29.4, 'LEFT', 'LB', 77, 'FOUR', 'THREE', 68, 59, 78),
(2, 2, 170, 68.6, 32.5, 'RIGHT', 'LW', 65, 'FIVE', 'FIVE', 88, 70, 80),
(3, 3, 173, 70.2, 28.4, 'RIGHT', 'LM', 88, 'FOUR', 'THREE', 83, 63, 89),
(4, 4, 165, 65.3, 30.4, 'RIGHT', 'LW', 66, 'TWO', 'FOUR', 92, 77, 72),
(5, 5, 166, 66.2, 29.3, 'RIGHT', 'LM', 81, 'THREE', 'THREE', 82, 64, 89),
(6, 6, 190, 86.9, 26.4, 'LEFT', 'CB', 72, 'THREE', 'ONE', 44, 54, 85),
(7, 7, 201, 90.3, 26.3, 'RIGHT', 'GK', 0, 'THREE', 'ONE', 32, 0, 77),
(8, 8, 189, 88.4, 24.5, 'RIGHT', 'GK', 0, 'FOUR', 'ONE', 88, 0, 86),
(9, 9, 191, 83.5, 27.8, 'RIGHT', 'CB', 79, 'ONE', 'THREE', 62, 58, 79),
(10, 10, 175, 73.4, 30.8, 'RIGHT', 'LM', 89, 'THREE', 'FOUR', 93, 64, 83),
(11, 11, 177, 71.9, 30.9, 'RIGHT', 'RM', 82, 'ONE', 'THREE', 86, 78, 79),
(12, 12, 179, 69.4, 28.9, 'LEFT', 'LM', 80, 'FOUR', 'TWO', 89, 86, 72),
(13, 13, 170, 67.4, 32.7, 'RIGHT', 'RW', 69, 'ONE', 'FOUR', 95, 85, 69),
(14, 14, 172, 66.4, 33.2, 'RIGHT', 'CM', 95, 'FIVE', 'THREE', 76, 72, 87),
(15, 15, 182, 72.4, 31.7, 'RIGHT', 'CM', 83, 'THREE', 'THREE', 75, 65, 85),
(16, 16, 183, 77.3, 29.4, 'RIGHT', 'CM', 84, 'TWO', 'TWO', 80, 63, 92),
(17, 17, 185, 74.1, 28.4, 'RIGHT', 'CM', 84, 'FOUR', 'FOUR', 82, 69, 80),
(18, 18, 188, 78.7, 27.6, 'RIGHT', 'LB', 71, 'FIVE', 'THREE', 65, 64, 92),
(19, 19, 167, 65.8, 30.8, 'LEFT', 'LW', 70, 'THREE', 'TWO', 86, 77, 79),
(20, 20, 169, 68.8, 36.5, 'RIGHT', 'RW', 64, 'FOUR', 'FIVE', 90, 79, 75),
(21, 21, 180, 71.4, 27.9, 'RIGHT', 'LW', 61, 'FIVE', 'THREE', 96, 82, 90),
(22, 22, 181, 73.9, 28.8, 'RIGHT', 'RM', 78, 'TWO', 'THREE', 84, 76, 86),
(23, 23, 195, 80.9, 26.5, 'RIGHT', 'CB', 74, 'THREE', 'ONE', 70, 62, 82),
(24, 24, 193, 85.4, 25.8, 'RIGHT', 'RB', 77, 'ONE', 'THREE', 66, 60, 81),
(25, 25, 190, 88.4, 28.3, 'LEFT', 'CB', 82, 'FOUR', 'TWO', 69, 57, 80),
(26, 26, 187, 78.1, 27.5, 'RIGHT', 'RB', 80, 'TWO', 'THREE', 73, 73, 71),
(27, 27, 186, 79.3, 27.6, 'LEFT', 'LB', 64, 'FOUR', 'ONE', 72, 70, 75),
(28, 28, 187, 74.3, 26.3, 'LEFT', 'LB', 67, 'FIVE', 'THREE', 64, 53, 74),
(29, 29, 178, 68.4, 29.8, 'RIGHT', 'RM', 76, 'THREE', 'THREE', 83, 64, 89),
(30, 30, 179, 69.2, 35.4, 'RIGHT', 'S', 60, 'FOUR', 'FIVE', 88, 90, 69),
(31, 31, 176, 81.2, 30.1, 'RIGHT', 'S', 63, 'TWO', 'THREE', 88, 93, 80),
(32, 32, 169, 71.3, 32.7, 'RIGHT', 'S', 75, 'ONE', 'FIVE', 82, 95, 78),
(33, 33, 164, 64.2, 34.8, 'RIGHT', 'S', 70, 'THREE', 'THREE', 90, 86, 82),
(34, 34, 184, 79.9, 29.1, 'RIGHT', 'RW', 61, 'FOUR', 'THREE', 87, 76, 83),
(35, 35, 192, 76.2, 30.2, 'RIGHT', 'CB', 74, 'TWO', 'ONE', 65, 58, 72),
(36, 36, 191, 82.4, 26.3, 'RIGHT', 'CB', 78, 'THREE', 'THREE', 56, 56, 78),
(37, 37, 193, 83.6, 30.7, 'RIGHT', 'RB', 77, 'FOUR', 'THREE', 59, 48, 76),
(38, 38, 194, 78.8, 26.2, 'LEFT', 'GK', 0, 'FOUR', 'ONE', 38, 0, 75),
(39, 39, 181, 76.3, 29.6, 'RIGHT', 'RW', 59, 'TWO', 'FIVE', 89, 67, 83),
(40, 40, 182, 69.2, 36.7, 'RIGHT', 'RM', 87, 'THREE', 'THREE', 79, 69, 95),
(41, 41, 183, 72.7, 31.9, 'RIGHT', 'GK', 0, 'THREE', 'ONE', 40, 0, 69),
(42, 42, 184, 73.2, 33.8, 'LEFT', 'CB', 77, 'FOUR', 'THREE', 76, 64, 76),
(43, 43, 186, 77.3, 29.8, 'RIGHT', 'RB', 73, 'TWO', 'TWO', 62, 86, 82),
(44, 44, 188, 75.4, 34.5, 'RIGHT', 'CB', 71, 'TWO', 'TWO', 65, 75, 88);

--                            ('ROLE_PLAYER'),
--                            ('ROLE_TRAINER'),
--                            ('ROLE_CLUBEMPLOYEE');


INSERT INTO users(username,password,enabled,email) VALUES
('baian','$2a$10$C0oa49sVgc2lGmcXUHRGbueX0nT7f2tb1g4wWxdjCD2nukdCOwckW', true,'baian@gmail.com'),  -- password: pass
('yannick','$2a$10$C0oa49sVgc2lGmcXUHRGbueX0nT7f2tb1g4wWxdjCD2nukdCOwckW', true,'yannick@gmail.com'),
('sjoerd','$2a$10$C0oa49sVgc2lGmcXUHRGbueX0nT7f2tb1g4wWxdjCD2nukdCOwckW', true,'sjoerd@gmail.com');

INSERT INTO authorities(username,authority, user_username) VALUES
                                                 ('baian','ROLE_USER', 'baian'),

                                                 ('yannick','ROLE_TRAINER', 'yannick'),
                                                 ('yannick','ROLE_USER', 'yannick'),

                                                 ('sjoerd','ROLE_TRAINER', 'sjoerd'),
                                                 ('sjoerd','ROLE_USER', 'sjoerd');