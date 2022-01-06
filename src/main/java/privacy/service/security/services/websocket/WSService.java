package privacy.service.security.services.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import privacy.dao.websocket.PatientRepository;
import privacy.dao.websocket.WebsocketRepository;
import privacy.general.payload.websocket.MessageDTO;
import privacy.general.payload.websocket.PatientDTO;
import privacy.models.Patient;
import privacy.models.websocket.ResponseMessage;
import privacy.models.websocket.WSMessage;
import privacy.service.security.services.OwnerDetailsServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;
    @Autowired
    private WebsocketRepository websocketRepository;
    @Autowired
    private OwnerDetailsServiceImpl ownerDetailsServiceImpl;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message) {
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        ResponseMessage response = new ResponseMessage(sender,message);
        notificationService.sendGlobalNotification();
        messagingTemplate.convertAndSend("/topic/messages", response);
        saveMessageToDB(new MessageDTO(sender, message));

    }

    public void notifyUser(final String id, final String message) {
        Long sender = ownerDetailsServiceImpl.getUserIdFromToken();
        ResponseMessage response = new ResponseMessage(sender, message);
        notificationService.privateMessage(id);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
        saveMessageToDB(new MessageDTO(sender, message));
    }

    public void saveNewPatient(final Patient patient) {
//        Long doctor = ownerDetailsServiceImpl.getUserIdFromToken();
//        notificationService.privateMessage(id);
        patientRepository.save(patient);
    }

    public PatientDTO findPatientById(final Long id){
        Optional<Patient> patientById = patientRepository.findPatientByPatientId(id);
        if(patientById.isPresent()){
            return new PatientDTO(patientById.get().getName(), patientById.get().getAge(), patientById.get().getCondition());
        }else{
            return null;
        }
    }

    public List<Patient> findPatientByUsername(final String userName){
        List<Patient> patientByUsername = patientRepository.findPatientsByNameContains(userName);
        return patientByUsername;
    }

    public List<Patient> findPatientByAge(final int age1, final int age2){
        List<Patient> patients = patientRepository.findAllByAgeBetween(age1, age2);
        return patients;
    }

    public void saveMessageToDB(MessageDTO messageDTO){
        websocketRepository.save(new WSMessage(messageDTO.getSenderId(), messageDTO.getMessageContent()));
    }
}
