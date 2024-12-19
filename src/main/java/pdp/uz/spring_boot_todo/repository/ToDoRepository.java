package pdp.uz.spring_boot_todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.spring_boot_todo.entity.TodoEntity;

import java.util.List;
import java.util.UUID;

public interface ToDoRepository extends JpaRepository<TodoEntity, UUID> {
    List<TodoEntity> findByUser_Id(UUID userId);
}
