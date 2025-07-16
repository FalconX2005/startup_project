package uz.pdp.startupproject.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;
import uz.pdp.startupproject.enums.Gender;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Employee extends AbsLongEntity {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
}
