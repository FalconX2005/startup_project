package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.startupproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}