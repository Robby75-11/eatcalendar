package app.eat.controller;

import app.eat.dto.LoginDto;
import app.eat.dto.RegisterDto;
import app.eat.dto.AuthResponse;
import app.eat.enumeration.Role;
import app.eat.model.User;
import app.eat.repository.UserRepository;
import app.eat.security.JwtTool;
import app.eat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTool jwtTool;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, JwtTool jwtTool, UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtTool = jwtTool;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // prendi UserDetails (non il tuo User)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // carica l’utente vero dal DB
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // genera token
        String jwt = jwtTool.createToken(user);

        return ResponseEntity.ok(Map.of(
                "token", jwt,
                "userId", user.getId()
        ));
    }
    // 🆕 REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (userRepo.findByUsername(registerDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("⚠️ Username già esistente");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // 🔒 password cifrata
        user.setRole(registerDto.getRole() != null ? registerDto.getRole() : Role.USER);

        userRepo.save(user);

        return ResponseEntity.ok("✅ Utente registrato con successo!");
    }
}