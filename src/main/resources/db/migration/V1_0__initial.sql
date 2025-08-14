CREATE TABLE attachment
(
    id        VARCHAR(36)  NOT NULL,
    name      VARCHAR(255) NULL,
    mime_type VARCHAR(255) NULL,
    content   LONGTEXT     NULL,
    CONSTRAINT pk_attachment PRIMARY KEY (id)
);

CREATE TABLE department
(
    id   VARCHAR(36)  NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_department PRIMARY KEY (id)
);

CREATE TABLE message
(
    id            VARCHAR(36) NOT NULL,
    message_id    VARCHAR(36) NULL,
    batch_id      VARCHAR(36) NULL,
    type          VARCHAR(50) NULL,
    status        VARCHAR(50) NULL,
    text          TEXT        NULL,
    user_id       VARCHAR(36) NULL,
    department_id VARCHAR(36) NULL,
    CONSTRAINT pk_message PRIMARY KEY (id)
);

CREATE TABLE message_attachment
(
    attachment_id VARCHAR(36) NOT NULL,
    message_id    VARCHAR(36) NOT NULL
);

CREATE TABLE user
(
    id   VARCHAR(36)  NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

ALTER TABLE message
    ADD CONSTRAINT FK_MESSAGE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE message_attachment
    ADD CONSTRAINT fk_mesatt_on_attachment_entity FOREIGN KEY (attachment_id) REFERENCES attachment (id);

ALTER TABLE message_attachment
    ADD CONSTRAINT fk_mesatt_on_message_entity FOREIGN KEY (message_id) REFERENCES message (id);
