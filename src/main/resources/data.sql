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
                                     (5, 'Bayern Munchen');

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

INSERT INTO trainer (id, trainer_name, house_number) VALUES
                           (1, 'Jorgen', 55),
                           (2, 'Michael', 102),
                           (3, 'Rutger', 2),
                           (4, 'Wouter', 1005),
                           (5, 'Rene', 38),
                           (6, 'Harry', 29),
                           (7, 'Peter', 47),
                           (8, 'Vincent', 98);

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

INSERT INTO player_data(id, player_id, height, weight, top_speed, prefered_foot, position, work_rate, weak_foot, skill_moves, dribbling, jumping, passing) VALUES
(1, 1, 188, 75, 33, 'RIGHT', 'LB', 77, null, 'THREE', 88, 70, 80);

-- INSERT INTO role(name) VALUES
--                            ('ROLE_PLAYER'),
--                            ('ROLE_TRAINER'),
--                            ('ROLE_CLUBEMPLOYEE');

INSERT INTO authority(id,username,authority)
VALUES (1, 'baian','ROLE_PLAYER');

INSERT INTO users(username,password,enabled,email)
VALUES ('baian','$2a$10$C0oa49sVgc2lGmcXUHRGbueX0nT7f2tb1g4wWxdjCD2nukdCOwckW',false,'koolblue');
