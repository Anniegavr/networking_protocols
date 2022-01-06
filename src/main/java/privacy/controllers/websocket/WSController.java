package privacy.controllers.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privacy.models.Patient;
import privacy.models.websocket.WSMessage;
import privacy.service.security.services.websocket.WSService;

@RestController
@RequestMapping("api/auth/")
public class WSController {

    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final WSMessage message) {
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final WSMessage message) {
        service.notifyUser(id, message.getMessageContent());
    }

    @PostMapping("/save_patient")
    public void sendPrivateMessage(
                                   @RequestBody final Patient patient) {
        service.saveNewPatient(patient);
    }


}
