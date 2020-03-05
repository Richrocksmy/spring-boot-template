CREATE TABLE message
(
    id            VARCHAR(36) NOT NULL,
    message_text  TEXT,
    created_time  DATETIME,

    PRIMARY KEY (id)
);

