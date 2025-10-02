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
INSERT INTO message (id, subject, municipality_id, department_id, user_id, message_type, created)
VALUES ('message1', 'This is the subject', '2281', 'department1', 'user1', 'LETTER', '2025-09-05 10:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient1', 'message1', 'SNAIL_MAIL', 'SENT', '2025-09-05 10:01:00'),
       ('recipient2', 'message1', 'SNAIL_MAIL', 'SENT', '2025-09-05 10:01:00'),
       ('recipient3', 'message1', 'SNAIL_MAIL', 'FAILED', '2025-09-05 10:01:00');

-- 2 DIGITAL_MAIL, 1 SMS
INSERT INTO message (id, subject, municipality_id, department_id, user_id, message_type, created)
VALUES ('message2', 'This is the subject', '2281', 'department2', 'user1', 'LETTER', '2025-09-06 11:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient4', 'message2', 'DIGITAL_MAIL', 'SENT', '2025-09-06 11:01:00'),
       ('recipient5', 'message2', 'DIGITAL_MAIL', 'SENT', '2025-09-06 11:01:00'),
       ('recipient6', 'message2', 'SMS', 'SENT', '2025-09-06 11:02:00');

-- 2 DIGITAL_REGISTERED_LETTER
INSERT INTO message (id, subject, municipality_id, department_id, user_id, message_type, created)
VALUES ('message3', 'This is the subject', '2281', 'department3', 'user2', 'LETTER', '2025-09-07 12:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient7', 'message3', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-09-07 12:01:00'),
       ('recipient8', 'message3', 'DIGITAL_REGISTERED_LETTER', 'FAILED', '2025-09-07 12:01:00');

-- 3 SMS
INSERT INTO message (id, subject, municipality_id, department_id, user_id, message_type, created)
VALUES ('message4', 'This is the subject', '2281', 'department4', 'user2', 'LETTER', '2025-09-08 13:00:00');

INSERT INTO recipient (id, message_id, type, status, created)
VALUES ('recipient9', 'message4', 'SMS', 'SENT', '2025-09-08 13:01:00'),
       ('recipient10', 'message4', 'SMS', 'SENT', '2025-09-08 13:01:00'),
       ('recipient11', 'message4', 'SMS', 'NOT_SENT', '2025-09-08 13:01:00');

-- 1 SNAIL_MAIL, 1 DIGITAL_MAIL, 1 SMS
INSERT INTO message (id, subject, municipality_id, department_id, user_id, message_type, created)
VALUES ('message5', 'This is the subject', '2281', 'department5', 'user1', 'LETTER', '2025-09-09 14:00:00');

INSERT INTO attachment(id, file_name, content_type, created, message_id)
VALUES ('attachment1', 'document.pdf', 'application/pdf', '2025-08-05 10:00:00', 'message5'),
       ('attachment2', 'image.png', 'image/png', '2025-08-05 10:00:00', 'message5');

INSERT INTO recipient (id, message_id, type, status, created, phone_number, address, zip_code, city)
VALUES ('recipient12', 'message5', 'SNAIL_MAIL', 'SENT', '2025-09-09 14:01:00', null, 'Testgatan 1', '12345',
        'Testsvall'),
       ('recipient13', 'message5', 'DIGITAL_MAIL', 'FAILED', '2025-09-09 14:01:00', null, null, null, null),
       ('recipient14', 'message5', 'SMS', 'SENT', '2025-09-09 14:02:00', '+46123456789', null, null, null);



-- ====================================================================
-- Test data for august 2025
-- ====================================================================
INSERT INTO message (id, municipality_id, department_id, user_id, message_type, created)
VALUES ('message101', '2281', 'department1', 'user1', 'LETTER', '2025-08-05 10:00:00');

INSERT INTO attachment(id, file_name, content_type, created, message_id)
VALUES ('attachment3', 'document.pdf', 'application/pdf', '2025-08-05 10:00:00', 'message101'),
       ('attachment4', 'image.png', 'image/png', '2025-08-05 10:00:00', 'message101');

-- 25 SNAIL_MAIL, 5 DIGITAL_MAIL, 5 SMS
INSERT INTO recipient (id, first_name, last_name, message_id, type, status, created, phone_number, address, zip_code,
                       city)
VALUES
-- 25 snail mail
('recipient101', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 12', '12345', 'Testsvall'),
('recipient102', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 13', '12345', 'Testsvall'),
('recipient103', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 14', '12345', 'Testsvall'),
('recipient104', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 15', '12345', 'Testsvall'),
('recipient105', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'FAILED', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 16', '12345', 'Testsvall'),
('recipient106', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 17', '12345', 'Testsvall'),
('recipient107', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 18', '12345', 'Testsvall'),
('recipient108', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', null, 'Testgatan 121',
 '12345', 'Testsvall'),
('recipient109', 'John', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 19', '12345', 'Testsvall'),
('recipient110', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 122', '12345', 'Testsvall'),
('recipient111', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 123', '12345', 'Testsvall'),
('recipient112', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 124', '12345', 'Testsvall'),
('recipient113', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', null, 'Testgatan 12',
 '12345', 'Testsvall'),
('recipient114', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 125', '12345', 'Testsvall'),
('recipient115', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 126', '12345', 'Testsvall'),
('recipient116', 'Alex', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 127', '12345', 'Testsvall'),
('recipient117', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 128', '12345', 'Testsvall'),
('recipient118', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', null, 'Testgatan 129',
 '12345', 'Testsvall'),
('recipient119', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 120', '12345', 'Testsvall'),
('recipient120', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 112', '12345', 'Testsvall'),
('recipient121', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 1122', '12345', 'Testsvall'),
('recipient122', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 122', '12345', 'Testsvall'),
('recipient123', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 132', '12345', 'Testsvall'),
('recipient124', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', '+46123456789',
 'Testgatan 131', '12345', 'Testsvall'),
('recipient125', 'Jane', 'Doe', 'message101', 'SNAIL_MAIL', 'SENT', '2025-08-05 10:01:00', null, 'Testgatan 182',
 '12345', 'Testsvall'),

-- 5 digital mail
('recipient126', 'John', 'Wick', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00', null, null, null, null),
('recipient127', 'John', 'Wick', 'message101', 'DIGITAL_MAIL', 'FAILED', '2025-08-05 10:02:00', null, null, null, null),
('recipient128', 'John', 'Wick', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00', null, null, null, null),
('recipient129', 'John', 'Wick', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00', null, null, null, null),
('recipient130', 'John', 'Wick', 'message101', 'DIGITAL_MAIL', 'SENT', '2025-08-05 10:02:00', null, null, null, null),

-- 5 sms
('recipient131', 'John', 'Wick', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00', '+46123456789', null, null, null),
('recipient132', 'John', 'Wick', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00', '+46123456789', null, null, null),
('recipient133', 'John', 'Wick', 'message101', 'SMS', 'FAILED', '2025-08-05 10:03:00', '+46123456789', null, null,
 null),
('recipient134', 'John', 'Wick', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00', '+46123456789', null, null, null),
('recipient135', 'John', 'Wick', 'message101', 'SMS', 'SENT', '2025-08-05 10:03:00', '+46123456789', null, null, null);



INSERT INTO message (id, municipality_id, department_id, user_id, message_type, created)
VALUES ('message102', '2281', 'department2', 'user2', 'LETTER', '2025-08-06 12:00:00');

-- 20 DIGITAL_MAIL, 10 DIGITAL_REGISTERED_LETTER, 5 SMS
INSERT INTO recipient (id, message_id, type, status, created, phone_number)
VALUES
-- 20 digital mail
('recipient201', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient202', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient203', 'message102', 'DIGITAL_MAIL', 'FAILED', '2025-08-06 12:01:00', null),
('recipient204', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient205', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient206', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient207', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient208', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient209', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient210', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient211', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient212', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient213', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient214', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient215', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient216', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient217', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient218', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient219', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),
('recipient220', 'message102', 'DIGITAL_MAIL', 'SENT', '2025-08-06 12:01:00', null),

-- 10 registered letters
('recipient221', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient222', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient223', 'message102', 'DIGITAL_REGISTERED_LETTER', 'FAILED', '2025-08-06 12:02:00', null),
('recipient224', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient225', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient226', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient227', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient228', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient229', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),
('recipient230', 'message102', 'DIGITAL_REGISTERED_LETTER', 'SENT', '2025-08-06 12:02:00', null),

-- 5 sms
('recipient231', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00', '+46123456789'),
('recipient232', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00', '+46123456789'),
('recipient233', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00', '+46123456789'),
('recipient234', 'message102', 'SMS', 'FAILED', '2025-08-06 12:03:00', '+46123456789'),
('recipient235', 'message102', 'SMS', 'SENT', '2025-08-06 12:03:00', '+46123456789');
