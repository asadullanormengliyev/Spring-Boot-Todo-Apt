package pdp.uz.spring_boot_todo.dto;

import lombok.*;
import pdp.uz.spring_boot_todo.entity.Status;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class TodoCreateResponse {
    private UUID id;
    private String title;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Status status;
}
