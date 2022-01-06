package privacy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import privacy.service.security.services.websocket.WSService;

@RestController
@RequestMapping("api/auth/")
public class PatientController {
    @Autowired
    private WSService service;

    @GetMapping("/find_patient_by_id")
    public void findPatientById(
            @RequestBody final Long patientId) {
        service.findPatientById(patientId);
    }

    @GetMapping("/find_patient_by_name")
    public void findPatientByName(
            @RequestBody final String patientName) {
        service.findPatientByUsername(patientName);
    }

    @GetMapping("/find_patient_by_age")
    public void findPatientByAge(
            @RequestBody final int age1, @RequestBody final int age2) {
        service.findPatientByAge(age1, age2);
    }

}
