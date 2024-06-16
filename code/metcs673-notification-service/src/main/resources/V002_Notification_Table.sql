

-- Create the database
CREATE DATABASE metcs673_ns_db;

-- Create the Notification table
CREATE TABLE notification (
    message_id VARCHAR(255) PRIMARY KEY,
    "to" VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL
);




