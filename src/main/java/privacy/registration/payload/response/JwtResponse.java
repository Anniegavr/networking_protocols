package privacy.registration.payload.response;
import liquibase.pro.packaged.G;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long ownerId;
    private String username;
    private String email;
    private List<String> roles;
}
