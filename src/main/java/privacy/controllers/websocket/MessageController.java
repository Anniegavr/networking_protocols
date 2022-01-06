package privacy.controllers.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import privacy.models.websocket.WSMessage;
import privacy.models.websocket.ResponseMessage;
import privacy.service.security.services.OwnerDetailsServiceImpl;
import privacy.service.security.services.websocket.NotificationService;

import java.security.Principal;

@RestController
@RequestMapping("api/auth/")
public class MessageController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OwnerDetailsServiceImpl ownerDetailsServiceImpl;


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final WSMessage message) throws InterruptedException {
        Thread.sleep(1000);
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        notificationService.sendGlobalNotification();
        return new ResponseMessage(sender, HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/patient-info")
    @SendToUser("/topic/patient-info")
    public ResponseMessage getPrivateMessage(final WSMessage message,
                                             final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        notificationService.privateMessage(principal.getName());
        return new ResponseMessage(sender, HtmlUtils.htmlEscape("Message from: "+sender+
                "\nSending private message to user " + principal.getName() + ": "
                        + message.getMessageContent())
        );
    }
}
