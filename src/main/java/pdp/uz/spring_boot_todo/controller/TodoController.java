package pdp.uz.spring_boot_todo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pdp.uz.spring_boot_todo.dto.TodoCreateRequest;
import pdp.uz.spring_boot_todo.dto.TodoCreateResponse;
import pdp.uz.spring_boot_todo.dto.TodoUpdateRequest;
import pdp.uz.spring_boot_todo.dto.UserCreateResponse;
import pdp.uz.spring_boot_todo.service.TodoService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        UserCreateResponse loggedInUser = (UserCreateResponse) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        List<TodoCreateResponse> todoCreateResponses = todoService.todoList(loggedInUser.getId());
        model.addAttribute("todos", todoCreateResponses);
        return "todo/todo";
    }

    @PostMapping("/add")
    public String toAdd(TodoCreateRequest todoCreateRequest, HttpSession session) {
        UserCreateResponse loggedInUser = (UserCreateResponse) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }
        todoCreateRequest.setUserId(loggedInUser.getId());
        todoService.addTodo(todoCreateRequest);
        return "redirect:/todo/list";
    }

    @PostMapping("/complete/{id}")
    public String todoStatus(@PathVariable("id") UUID todoId){
        todoService.updateStatus(todoId);
        return "redirect:/todo/list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        todoService.deleteTodo(id);
        return "redirect:/todo/list";
    }

    @PostMapping("/update/{id}")
    public String updateTodo(@PathVariable UUID id, @ModelAttribute TodoUpdateRequest request){
        todoService.updateTodo(id,request);
        return "redirect:/todo/list";
    }

}
