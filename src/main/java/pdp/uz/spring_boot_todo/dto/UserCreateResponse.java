package pdp.uz.spring_boot_todo.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UserCreateResponse {
    private UUID id;
    private String name;
    private String username;
    private String password;
    private boolean success;
}

