package pdp.uz.spring_boot_todo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class UserLoginRequest {
    private String username;
    private String password;
}
