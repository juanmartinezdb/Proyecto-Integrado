package iesbelen.iterpolaris.dto;

import iesbelen.iterpolaris.domain.Role;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
