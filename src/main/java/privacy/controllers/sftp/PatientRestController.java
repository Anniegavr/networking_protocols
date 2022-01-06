//package privacy.controllers.sftp;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import privacy.models.Patient;
//
//@RestController
//@RequiredArgsConstructor
//public class PatientRestController {
//    private final PatientGateway gateway;
//
//    @GetMapping("/api/spaceship/{name}")
//    public Patient newSpaceShip(@PathVariable String name, int age, String condition){
//        System.out.println("new Patient rest BEGIN");
//        Patient patient = new Patient(name,age,condition);
//        gateway.patientCreated(patient);
//        System.out.println("new Spaceship rest END");
//        return patient;
//    }
//}
