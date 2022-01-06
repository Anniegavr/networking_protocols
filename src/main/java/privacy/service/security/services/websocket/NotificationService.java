package privacy.service.security.services.websocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import privacy.dao.OwnerRepository;
import privacy.dao.websocket.WebsocketRepository;
import privacy.general.payload.websocket.MessageDTO;
import privacy.models.Patient;
import privacy.models.websocket.ResponseMessage;
import privacy.service.security.services.OwnerDetailsServiceImpl;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    private OwnerDetailsServiceImpl ownerDetailsServiceImpl;
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        ResponseMessage message = new ResponseMessage(sender, "Global Notification");
        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void privateMessage(final String userId) {
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        ResponseMessage message = new ResponseMessage(sender, "Private Notification");

        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
    }

    public void patientInfoSave(final Patient patient) {
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        String username = ownerRepository.getById(sender).getUsername();
        ResponseMessage message = new ResponseMessage(sender, "Patient Saved");
        messagingTemplate.convertAndSendToUser(username,"/topic/private-notifications", message);
    }


}
