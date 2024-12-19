package pdp.uz.spring_boot_todo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class UserCreateRequest {
    private String name;
    private String username;
    private String password;
}
