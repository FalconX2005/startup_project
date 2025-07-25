package uz.pdp.startupproject.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.pdp.startupproject.payload.LoginDTO;
import uz.pdp.startupproject.payload.RegisterDTO;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

/**
 * Created by: Umar
 * DateTime: 7/19/2025 2:32 PM
 */
@Service
public interface AuthService extends UserDetailsService {
    Object login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);

}
