package privacy.models.websocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class ResponseMessage {
    @Id
    @SequenceGenerator(
            name = "response_sequence",
            sequenceName = "response_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "response_sequence"
    )
    private Long ResponseId;

    public ResponseMessage(Long senderId, String content) {
        this.content = content;

    }

    private Long senderId;
    private String content;


}
