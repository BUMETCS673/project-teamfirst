CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE user_notifications (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    notification_id INT NOT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'unread',
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_notifications_user FOREIGN KEY (user_id) REFERENCES um_user(id),
    CONSTRAINT fk_user_notifications_notification FOREIGN KEY (notification_id) REFERENCES notifications(id)
);

INSERT INTO notifications (message, created_at)
VALUES (?, NOW());



SELECT
    n.id AS notification_id,
    n.message,
    un.status,
    un.sent_at
FROM
    notifications n
JOIN
    user_notifications un ON n.id = un.notification_id
WHERE
    un.user_id = ? AND un.status = 'unread';


SELECT
    n.id AS notification_id,
    n.message,
    un.status,
    un.sent_at
FROM
    notifications n
JOIN
    user_notifications un ON n.id = un.notification_id
WHERE
    un.user_id = ?;

