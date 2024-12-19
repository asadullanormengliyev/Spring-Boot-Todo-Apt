package pdp.uz.spring_boot_todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.uz.spring_boot_todo.dto.UserCreateRequest;
import pdp.uz.spring_boot_todo.dto.UserCreateResponse;
import pdp.uz.spring_boot_todo.dto.UserLoginRequest;
import pdp.uz.spring_boot_todo.entity.UserEntity;
import pdp.uz.spring_boot_todo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = UserEntity.builder()
                .name(userCreateRequest.getName())
                .username(userCreateRequest.getUsername())
                .password(userCreateRequest.getPassword())
                .build();
        UserEntity entity = userRepository.save(userEntity);
        return UserCreateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public List<UserCreateResponse> findAll() {
        List<UserEntity> repositoryAll = userRepository.findAll();
        List<UserCreateResponse> responses = new ArrayList<>();
        for (UserEntity userEntity : repositoryAll) {
            UserCreateResponse createResponse = UserCreateResponse.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .username(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .build();
            responses.add(createResponse);
        }
        return responses;
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public UserCreateResponse updateUser(UUID id, UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setName(userCreateRequest.getName());
        userEntity.setUsername(userCreateRequest.getUsername());
        userEntity.setPassword(userCreateRequest.getPassword());
        UserEntity entity = userRepository.save(userEntity);

       return UserCreateResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    public UserCreateResponse loginUser(UserLoginRequest userLoginRequest) {
        UserEntity userEntity = userRepository.findByUsername(userLoginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserCreateResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }

}
