package pdp.uz.spring_boot_todo.dto;


import lombok.*;
import pdp.uz.spring_boot_todo.entity.Status;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class TodoCreateRequest {
    private String title;
    private String description;
    private Status status;
    private UUID userId;
}

