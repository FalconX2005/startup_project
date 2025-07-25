package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.startupproject.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
