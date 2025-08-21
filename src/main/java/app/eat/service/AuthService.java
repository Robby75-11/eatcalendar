package app.eat.service;

import app.eat.dto.LoginDto;
import app.eat.dto.RegisterDto;
import app.eat.dto.AuthResponse;
import app.eat.model.User;
import app.eat.repository.UserRepository;
import app.eat.security.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTool jwtTool;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterDto dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());

        return userRepository.save(user);
    }

    public AuthResponse login(LoginDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));



        String token = jwtTool.createToken(user);
        return new AuthResponse(token);
    }
}

