package uz.pdp.startupproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.startupproject.entity.tempAbs.AbsLongEntity;
import uz.pdp.startupproject.enums.RoleEnum;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
public class User extends AbsLongEntity implements UserDetails {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
        return List.of();
    }

    private String resetCode;

    private Timestamp resetCodeExpireAt;

    @Column(unique = true)
    private String phoneNumber;

    @Column(name = "telegram_chat_id")
    private Long telegramChatId;



}
