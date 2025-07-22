package uz.pdp.startupproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.startupproject.entity.Attachment;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findAttachmentById(long id);
}