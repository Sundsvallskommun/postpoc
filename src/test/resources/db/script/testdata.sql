-- Users
INSERT INTO user (id, name)
VALUES ('user1', 'Anna'),
       ('user2', 'Björn');

-- Departments
INSERT INTO department (id, name)
VALUES ('department1', 'Miljöförvaltningen'),
       ('department2', 'Socialförvaltningen'),
       ('department3', 'IT-avdelningen'),
       ('department4', 'HR-avdelningen'),
       ('department5', 'Kulturförvaltningen');

-- ====================================================================
-- Test data for august 2025
-- ====================================================================
-- 3 SNAIL_MAIL
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message1', 'department1', 'user1', 'LETTER', '2025-09-05 10:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient1', 'message1', 'SNAIL_MAIL', 'SENT', '2025-09-05 10:01:00'),
       ('recipient2', 'message1', 'SNAIL_MAIL', 'SENT', '2025-09-05 10:01:00'),
       ('recipient3', 'message1', 'SNAIL_MAIL', 'FAILED', '2025-09-05 10:01:00');

-- 2 DIGITAL_MAIL, 1 SMS
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message2', 'department2', 'user1', 'LETTER', '2025-09-06 11:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient4', 'message2', 'DIGITAL_MAIL', 'SENT', '2025-09-06 11:01:00'),
       ('recipient5', 'message2', 'DIGITAL_MAIL', 'SENT', '2025-09-06 11:01:00'),
       ('recipient6', 'message2', 'SMS', 'SENT', '2025-09-06 11:02:00');

-- 2 DIGITAL_REGISTERED_LETTER
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message3', 'department3', 'user2', 'LETTER', '2025-09-07 12:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient7', 'message3', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-09-07 12:01:00'),
       ('recipient8', 'message3', 'DIGITAL_REGISTERED_LETTER', 'FAILED', '2025-09-07 12:01:00');

-- 3 SMS
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message4', 'department4', 'user2', 'LETTER', '2025-09-08 13:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient9', 'message4', 'SMS', 'SENT', '2025-09-08 13:01:00'),
       ('recipient10', 'message4', 'SMS', 'SENT', '2025-09-08 13:01:00'),
       ('recipient11', 'message4', 'SMS', 'NOT_SENT', '2025-09-08 13:01:00');

-- 1 SNAIL_MAIL, 1 DIGITAL_MAIL, 1 SMS
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message5', 'department5', 'user1', 'LETTER', '2025-09-09 14:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient12', 'message5', 'SNAIL_MAIL', 'SENT', '2025-09-09 14:01:00'),
       ('recipient13', 'message5', 'DIGITAL_MAIL', 'FAILED', '2025-09-09 14:01:00'),
       ('recipient14', 'message5', 'SMS', 'SENT', '2025-09-09 14:02:00');



-- ====================================================================
-- Test data for august 2025
-- ====================================================================
INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message101', 'department1', 'user1', 'LETTER', '2025-08-05 10:00:00');

-- 25 SNAIL_MAIL, 5 DIGITAL_MAIL, 5 SMS
INSERT INTO recipient (id, message_id, type, status, created)
VALUES
-- 25 snail mail
('recipient101', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient102', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient103', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient104', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient105', 'message101', 'SNAIL_MAIL', 'FAILED', '2025-08-05 10:01:00'),
('recipient106', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient107', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient108', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient109', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient110', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient111', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient112', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient113', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient114', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient115', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient116', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient117', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient118', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient119', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient120', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient121', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient122', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient123', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient124', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),
('recipient125', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00'),

-- 5 digital mail
('recipient126', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00'),
('recipient127', 'message101', 'DIGITAL_MAIL', 'FAILED', '2025-08-05 10:02:00'),
('recipient128', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00'),
('recipient129', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00'),
('recipient130', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00'),

-- 5 sms
('recipient131', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00'),
('recipient132', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00'),
('recipient133', 'message101', 'SMS', 'FAILED', '2025-08-05 10:03:00'),
('recipient134', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00'),
('recipient135', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00');



INSERT INTO message (id, department_id, user_id, message_type, created)
VALUES ('message102', 'department2', 'user2', 'LETTER', '2025-08-06 12:00:00');

-- 20 DIGITAL_MAIL, 10 DIGITAL_REGISTERED_LETTER, 5 SMS
INSERT INTO recipient (id, message_id, type, status, created)
VALUES
-- 20 digital mail
('recipient201', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient202', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient203', 'message102', 'DIGITAL_MAIL', 'FAILED', '2025-08-06 12:01:00'),
('recipient204', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient205', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient206', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient207', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient208', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient209', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient210', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient211', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient212', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient213', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient214', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient215', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient216', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient217', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient218', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient219', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),
('recipient220', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00'),

-- 10 registered letters
('recipient221', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient222', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient223', 'message102', 'DIGITAL_REGISTERED_LETTER', 'FAILED', '2025-08-06 12:02:00'),
('recipient224', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient225', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient226', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient227', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient228', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient229', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),
('recipient230', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00'),

-- 5 sms
('recipient231', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00'),
('recipient232', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00'),
('recipient233', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00'),
('recipient234', 'message102', 'SMS', 'FAILED', '2025-08-06 12:03:00'),
('recipient235', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00');
