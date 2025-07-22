package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 employee_branch
import uz.pdp.startupproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

// import org.springframework.stereotype.Repository;
// import uz.pdp.startupproject.entity.User;

// import java.util.List;
// import java.util.Optional;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     Optional<User> findByUsername(String username);
// }
//  client
