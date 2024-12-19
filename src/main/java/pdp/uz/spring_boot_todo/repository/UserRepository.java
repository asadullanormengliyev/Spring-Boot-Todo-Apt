package pdp.uz.spring_boot_todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.spring_boot_todo.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
     Optional<UserEntity> findByUsername(String username);
     Optional<UserEntity> findById(UUID uuid);
}
