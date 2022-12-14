package wa.user.api.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String idUser;
    private String name;
    private String document;
    private Instant createdAt;
    private Instant updatedAt;
}
