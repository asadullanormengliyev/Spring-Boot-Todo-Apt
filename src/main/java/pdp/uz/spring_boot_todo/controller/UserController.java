package pdp.uz.spring_boot_todo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pdp.uz.spring_boot_todo.dto.UserCreateRequest;
import pdp.uz.spring_boot_todo.dto.UserCreateResponse;
import pdp.uz.spring_boot_todo.dto.UserLoginRequest;
import pdp.uz.spring_boot_todo.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model) {
        List<UserCreateResponse> all = userService.findAll();
        model.addAttribute("users", all);
        return "user/list";
    }

    @PostMapping("/register")
    public String register(UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String login(UserLoginRequest userLoginRequest, HttpSession session) {
        UserCreateResponse user = userService.loginUser(userLoginRequest);
        session.setAttribute("loggedInUser", user);
        return "redirect:/todo/list";
    }

    @GetMapping("/register")
    public String register(){
        return "user/register";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/delete")
    public String deleteUser(UUID id) {
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }
}
