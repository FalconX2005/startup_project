package uz.pdp.startupproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.startupproject.payload.ApiResult;
import uz.pdp.startupproject.payload.LoginDTO;
import uz.pdp.startupproject.payload.RegisterDTO;
import uz.pdp.startupproject.security.AuthService;

/**
 * Created by: Umar
 * DateTime: 7/20/2025 4:59 PM
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterDTO registerDTO) {
        String register = authService.register(registerDTO);
        return ResponseEntity.ok(register);
    }
}
