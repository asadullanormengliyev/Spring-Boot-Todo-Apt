package pdp.uz.spring_boot_todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.spring_boot_todo.dto.TodoCreateRequest;
import pdp.uz.spring_boot_todo.dto.TodoCreateResponse;
import pdp.uz.spring_boot_todo.dto.TodoUpdateRequest;
import pdp.uz.spring_boot_todo.entity.Status;
import pdp.uz.spring_boot_todo.entity.TodoEntity;
import pdp.uz.spring_boot_todo.entity.UserEntity;
import pdp.uz.spring_boot_todo.repository.ToDoRepository;
import pdp.uz.spring_boot_todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public void addTodo(TodoCreateRequest todoCreateRequest) {
        UserEntity userEntity = userRepository.findById(todoCreateRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TodoEntity todoEntity = TodoEntity.builder()
                .title(todoCreateRequest.getTitle())
                .description(todoCreateRequest.getDescription())
                .status(todoCreateRequest.getStatus() != null ? todoCreateRequest.getStatus() : Status.PENDING) // Default qiymat
                .created(new Date())
                .updated(new Date())
                .user(userEntity)
                .build();

        TodoEntity entity = toDoRepository.save(todoEntity);
        TodoCreateResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createDate(entity.getCreated())
                .updateDate(entity.getUpdated())
                .build();
    }

    public List<TodoCreateResponse> todoList(UUID userId) {
        List<TodoEntity> todoEntityList = toDoRepository.findByUser_Id(userId);
        List<TodoCreateResponse> todoCreateResponses = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntityList) {
            TodoCreateResponse todoCreateResponse = TodoCreateResponse.builder()
                    .id(todoEntity.getId())
                    .title(todoEntity.getTitle())
                    .description(todoEntity.getDescription())
                    .createDate(todoEntity.getCreated())
                    .updateDate(todoEntity.getUpdated())
                    .status(todoEntity.getStatus())
                    .build();
            todoCreateResponses.add(todoCreateResponse);
        }
        return todoCreateResponses;
    }

    public void updateStatus(UUID todoId) {
        TodoEntity todoEntity = toDoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("todo not found"));
        todoEntity.setStatus(Status.COMPLETED);
        todoEntity.setUpdated(new Date());
        toDoRepository.save(todoEntity);
    }

    public void deleteTodo(UUID id){
        TodoEntity todoEntity = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("todo not found"));
        toDoRepository.delete(todoEntity);
    }

    public void updateTodo(UUID id, TodoUpdateRequest request){
        TodoEntity todoEntity = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("todo not found"));
        todoEntity.setTitle(request.getTitle());
        todoEntity.setDescription(request.getDescription());
        todoEntity.setUpdated(new Date());
        todoEntity.setStatus(request.getStatus());
        toDoRepository.save(todoEntity);
    }

}
