package com.achlys20.Task.Management.Backend.Notification;

import com.achlys20.Task.Management.Backend.Project.Project;
import com.achlys20.Task.Management.Backend.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationRepository notificationRepository;

    public void sendTaskAssignedNotification(User recipient, String message){
        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setRecipient(recipient);

        simpMessagingTemplate.convertAndSendToUser(
                recipient.getUserName(),
                "/queue/notifications",
                message
        );
    }

    public void sendProjectMemberAddedNotification(User recipient, Project project) {

        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setMessage("You were added to project: " + project.getName());

        notificationRepository.save(notification);

        simpMessagingTemplate.convertAndSendToUser(
                recipient.getUserName(),
                "/queue/notifications",
                notification.getMessage()
        );
    }

}
