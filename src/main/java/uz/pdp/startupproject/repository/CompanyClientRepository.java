package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.startupproject.entity.CompanyClient;

import java.util.List;

@Repository
public interface CompanyClientRepository extends JpaRepository<CompanyClient,Long> {
    List<CompanyClient> findByCompanyId(Long companyId);

    List<CompanyClient> getByClientId(Long clientId);
}
