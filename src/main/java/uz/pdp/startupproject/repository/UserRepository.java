package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.startupproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> getUserByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByUsername(String username);

    Optional<User> findByResetCode(String resetCode);

}