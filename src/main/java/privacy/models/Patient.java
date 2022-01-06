package privacy.models;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long patientId;

    public Patient(String name, int age, String condition, Long personalId) {
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.personalId = personalId;
    }

    private String name;
    private int age;
    private String condition;
    private Long personalId;


    @Override
    public String toString() {
        return "Patient{" +
                "name='" + this.getName() + '\'' +
                ", age=" + this.getAge() +
                ", condition="+this.getCondition()+'}';
    }
}
