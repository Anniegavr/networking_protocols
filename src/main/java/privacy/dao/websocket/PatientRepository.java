package privacy.dao.websocket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import privacy.models.Patient;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByAgeBetween(int low, int high);
    Optional<Patient> findPatientByPatientId(Long id);
    List<Patient> findPatientsByNameContains(String nameRegex);
}
