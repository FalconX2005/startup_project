package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.startupproject.entity.Debts;

@Repository
public interface DebtsRepository extends JpaRepository<Debts,Long> {
}
