package pdp.uz.spring_boot_todo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @Column(length = 1000)
    private String description;
    private Date created;
    private Date updated;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private UserEntity user;
}
