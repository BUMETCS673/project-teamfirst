package edu.bu.metcs673_notification_service.repository;

import edu.bu.metcs673_notification_service.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
}
