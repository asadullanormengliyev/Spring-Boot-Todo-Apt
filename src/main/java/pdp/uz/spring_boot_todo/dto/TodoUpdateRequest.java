package pdp.uz.spring_boot_todo.dto;


import lombok.*;
import pdp.uz.spring_boot_todo.entity.Status;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class TodoUpdateRequest {
    private String title;
    private String description;
    private Status status;
}
